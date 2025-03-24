package com.agenda.desafio.src.main.domain.entity.cliente;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {

	private String endereco;
	
	public Endereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Endereco() {
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
