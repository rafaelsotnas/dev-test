package com.fourcadia.openBanking.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.fourcadia.openBanking.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		usuarioRepository.save(new Usuario(0L, "Roberto Rivellino", "10345678912", "robertorivellino@email.com.br", "222111550"));
		usuarioRepository.save(new Usuario(0L, "Roberto Clemente", "10345678912", "robertoclemente@email.com.br", "91113047"));
		usuarioRepository.save(new Usuario(0L, "Bobby Orr", "04123451789", "bobbyorr@email.com.br", "00128423"));
		usuarioRepository.save(new Usuario(0L, "Bob Gibson", "45123451780", "bobgibson@email.com.br", "64903004"));
	}	
	
	@Test
	@DisplayName("Retorna um usuário")
	void retornarUmUsuario() {
			Optional<Usuario> usuario = usuarioRepository.findByEmail("robertorivellino@email.com.br");
			assertTrue(usuario.get().getEmail().equals("robertorivellino@email.com.br"));
	}
	
	@AfterAll
	void end() {
		usuarioRepository.deleteAll();
	}
}