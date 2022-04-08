package com.fourcadia.openBanking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fourcadia.openBanking.model.Usuario;
import com.fourcadia.openBanking.service.UsuarioService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Test
	@Order(1)
	@DisplayName("Cadastrar usuário")
	void deveCriarUmUsuario() {
		
		/*Criação da requisição.*/
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L, "Joe Namath", 
				"83468098232", "joenamath@email.com.br", "123456789"));
		
		/*Passa endereço, verbo, requisição criada e o que se quer receber.*/
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuario/cadastrar", HttpMethod.POST, requisicao, Usuario.class);
		
		/*Confirmação se a resposta foi a esperada.*/
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		
		/*Checar se o que foi gravado no banco de dados tá correto.*/
		assertEquals(requisicao.getBody().getNome(), resposta.getBody().getNome());
	}

    @Test
    @Order(2)
    @DisplayName("Não deve permitir a duplicação do usuário")
    void naoDeveDuplicarUsuario() {
	
	usuarioService.cadastrarUsuario(new Usuario(0L, 
			"Sócrates", "13465278159", "socratesbrasileiro@email.com.br", "123456789"));
	
	/*Método de requisição.*/
	HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L, 
			"Sócrates", "13465278159", "socratesbrasileiro@email.com.br", "123456789"));
	
	ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuario/cadastrar", HttpMethod.POST, requisicao, Usuario.class);
	
	assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
    }
    
    @Test
	@Order(3)
	@DisplayName("Atualizar usuário")
	void deveAtualizarUmUsuario() {
		
		java.util.Optional<Usuario> usuarioCreate = usuarioService.cadastrarUsuario(new Usuario(0L, 
				"Sócrates", "13465278159", "socrates@email.com.br", "123456789"));
		
		/*Atualização dos dados.*/
		Usuario usuarioUpdate = new Usuario(usuarioCreate.get().getId(), 
				"Sugar Ray Robinson", "10465278159", "sugarray@email.com.br", "103456789");
		
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuarioUpdate);
		
		/*Autenticação.*/
		ResponseEntity<Usuario>resposta=testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/usuario/atualizar", HttpMethod.PUT, requisicao, Usuario.class);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		
		assertEquals(requisicao.getBody().getNome(), resposta.getBody().getNome());
	}
    
	@Test
	@Order(4)
	@DisplayName("Listar todos os usuários")
	void deveListarTodosOsUsuario() {
		
		usuarioService.cadastrarUsuario(new Usuario(0L, 
				"Dizzy Dean", "66402780004", "dizzydean@email.com.br", "99999999"));		
		usuarioService.cadastrarUsuario(new Usuario(0L, 
				"Sandy Koufax", "32425278030", "sandykoufax@email.com.br", "3232323232"));
		
		/*Checar lista de usuários.*/
		ResponseEntity<String> resposta = testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/usuario/listar", HttpMethod.GET, null, String.class);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	@Order(5)
	@DisplayName("Identificar usuários pelo ID")
	void deveIdentificarUsuariosPeloId() {
		
		usuarioService.cadastrarUsuario(new Usuario(0L, 
				"Dizzy Dean", "6640278000", "dizzydean@email.com.br", "99999999"));		
			usuarioService.cadastrarUsuario(new Usuario(0L, 
				"Sandy Koufax", "32425278030", "sandykoufax@email.com.br", "3232323232"));
			
		ResponseEntity<String> resposta = testRestTemplate
					.withBasicAuth("root", "root")
					.exchange("/usuario/1", HttpMethod.GET, null, String.class);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());		
	}
}
