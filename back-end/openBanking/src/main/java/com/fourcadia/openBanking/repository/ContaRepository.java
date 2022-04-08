package com.fourcadia.openBanking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fourcadia.openBanking.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{
	Optional<Conta> findById (Long id);
}