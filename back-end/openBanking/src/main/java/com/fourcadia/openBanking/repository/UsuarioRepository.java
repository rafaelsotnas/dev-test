package com.fourcadia.openBanking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fourcadia.openBanking.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String userName);
	
	/*Método pros testes.*/
	public List<Usuario> findAllByEmail(String email);
}