package com.fourcadia.openBanking.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fourcadia.openBanking.model.Usuario;
import com.fourcadia.openBanking.model.UsuarioLogin;
import com.fourcadia.openBanking.repository.UsuarioRepository;
import com.fourcadia.openBanking.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	/*Injeção de dependência da classe de serviço.*/
	@Autowired
	UsuarioService usuarioService;
	
	/*"                        " interface de repositório.*/
	@Autowired
	UsuarioRepository usuarioRepository;
	
	/*Metódos das requisições que serão feitas na aplicação
	 *através dos verbos get, post e put. Todos responderão 
	 *a uma requisição http através do ResponseEntity e 
	 *evocarão alguma função pros verbos. Abaixo retornarão 
	 *algum método, seja buscando no repositório ou na classe
	 *de serviço, e algum parâmetro específico guardado em si
	 *e declarado no último parêntese. A função .map e .orElse
	 *nos devolve um status http, onde recebemos uma confimação
	 *que a requisição foi feita com sucesso, e também podemos
	 *identificar com mais facilidade um possível erro e afins,
	 *tanto no JUnit quanto no Postman. */
	
	@GetMapping("/listar")
	public ResponseEntity<List<Usuario>> getAll() {
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable Long id) {
		return usuarioRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.cadastrarUsuario(usuario)
			.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> login(@Valid @RequestBody Optional<UsuarioLogin> usuarioLogin) {
		return usuarioService.autenticarUsuario(usuarioLogin)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.atualizarUsuario(usuario)
			.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
}