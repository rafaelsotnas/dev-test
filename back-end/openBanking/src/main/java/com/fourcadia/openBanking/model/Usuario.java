package com.fourcadia.openBanking.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@ComponentScan
@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	/*@Id indica a primary key do banco de dados.*/
	/*@GeneratedValue indica que essa PK será gerada automaticamente, 
	e delega a responsabilidade pro banco gerar as que virão em sequência.*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*Atributos da classe e algumas validações pra autenticar no front-end.*/
	@NotNull(message = "Nome é obrigatório.")
	private String nome;
	
	@Size(min = 11, message = "O CPF deve conter 11 dígitos.")
	private String cpf;
	
	@NotNull(message = "E-mail é obrigatório.")
	@Email(message = "Registre um e-mail válido.")
	private String email;
	
	@NotBlank(message = "Senha é obrigatória.")
	@Size(min = 8, message = "A senha deve conter, no mínimo, 8 caracteres.")
	private String senha;
	
	/*Relação um pra um na tabela do banco de dados.*/
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	
	/*Foreign key da classe usuário.*/
	private Conta conta;
	
	/*Método construtor com atributos.*/
	public Usuario(Long id, String nome, String cpf, String email, String senha) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
	}
	
	/*Método construtor sem atributos.*/
	public Usuario() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
}
