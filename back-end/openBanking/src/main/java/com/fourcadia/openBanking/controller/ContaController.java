package com.fourcadia.openBanking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fourcadia.openBanking.model.Conta;
import com.fourcadia.openBanking.repository.ContaRepository;

@RestController
@RequestMapping("/conta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContaController {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@GetMapping("/listar")
	public ResponseEntity<List<Conta>> getAll() {
		return ResponseEntity.ok(contaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Conta> getById(@PathVariable Long id) {
		return contaRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Conta> cadastrarConta(@Valid @RequestBody Conta conta) {
		return ResponseEntity.status(HttpStatus.CREATED).body(contaRepository.save(conta));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Conta> atualizarConta(@Valid @RequestBody Conta conta) {
		return contaRepository.findById(conta.getId())
				.map(resposta -> {
					return ResponseEntity.ok().body(contaRepository.save(conta));
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarConta(@Valid @PathVariable Long id) {
		return contaRepository.findById(id)
				.map(resposta -> {
					contaRepository.deleteById(id);
					
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}
