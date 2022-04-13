package com.fourcadia.openBanking.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fourcadia.openBanking.model.Usuario;
import com.fourcadia.openBanking.model.UsuarioLogin;
import com.fourcadia.openBanking.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
		
		/*Verifica se o usuário existe através do findByEmail e ativa a função booleana através do isPresent.*/
		if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
			
			/*Retorno de instância vazia.*/
			return Optional.empty();
		
		/*Se o usuário não existir, o método criptografarSenha é chamado pra fazer o trato da senha do novo usuário.*/
		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		
		/*Os dados são gravados dentro do Optional e é salvo dentro do parâmetro usuário.*/
		return Optional.of(usuarioRepository.save(usuario));
	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario) {	
		if(usuarioRepository.findById(usuario.getId()).isPresent()) {
			Optional<Usuario> buscaUsuario = usuarioRepository.findByEmail(usuario.getEmail());
			
				/*Verifica se o ID no banco de dados é o mesmo feito na requisição e, caso for diferente, 
				 *nos retorna um bad request e não é possível fazer a atualização.*/
				if ((buscaUsuario.isPresent()) && (buscaUsuario.get().getId() != usuario.getId()))
					throw new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
			
			/*Se o ID requisitado existe e for o mesmo do banco de dados, a senha será criptografada.*/
			usuario.setSenha(criptografarSenha(usuario.getSenha()));
			
			return Optional.ofNullable(usuarioRepository.save(usuario));	
		}
		return Optional.empty();
	}	

	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioLogin.get().getEmail());

		 if (usuario.isPresent()) {
			 
			 /*O método compararSenhas vai verificar a senha do banco de dados com a senha enviada pelo usuário.
			  *Se as senhas forem iguais o usuarioLogin será atualizado e o token será inserido nos dados através
			  *do método gerarBasicToken.*/
			if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setCpf(usuario.get().getCpf());
				usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getEmail(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				
				/*Retorno pra UsuarioController atualizado.*/
				return usuarioLogin;
			}
		}	
		
		return Optional.empty();	
	}

	private String criptografarSenha(String senha) {
		
		/*Instancia BCryptPasswordEncoder pra criptografar a senha.*/
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.encode(senha);
	}
	
	/*Checa se a senha enviada, depois de criptografada, é igual a senha do banco de dados.*/
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.matches(senhaDigitada, senhaBanco);
	}

	private String gerarBasicToken(String usuario, String senha) {
		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

	}
}
