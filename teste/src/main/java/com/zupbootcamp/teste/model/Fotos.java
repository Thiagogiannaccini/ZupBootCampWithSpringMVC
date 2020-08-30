package com.zupbootcamp.teste.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//Model das Fotos. Contem os atributos para postagens das Fotos com suas respecivas legendas (codigo, fotos, legenda)

@Entity
@Table(name = "Fotos")
public class Fotos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long codigo;

	@Column(name = "fotos")
	@Lob
	private byte[] fotos;

	@ManyToOne
		
	private Usuario usuario;

	@Column(name = "legenda")
	private String legenda;

	public byte[] getFotos() {
		return fotos;
	}

	public void setFotos(byte[] fotos) {
		this.fotos = fotos;
	}

	public String getLegenda() {
		return legenda;
	}

	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}
