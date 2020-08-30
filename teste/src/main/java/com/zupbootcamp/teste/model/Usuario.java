package com.zupbootcamp.teste.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

//Model do Usuario. Contem os atributos para cadastrar um Usuario na API(codigo, nomeUsuario, senha)

@Entity
@Table(name = "USUARIO")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotNull
	@Column(name = "nomeUsuario")
	private String nomeUsuario;

	@NotNull
	@Column(name = "senha")
	private String senha;

	@OneToMany
	
	private List<Fotos> fotos;

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
