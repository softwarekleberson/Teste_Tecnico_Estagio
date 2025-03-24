package com.agenda.desafio.src.main.domain.entity.contato;

import com.agenda.desafio.src.main.domain.entity.cliente.Cliente;
import com.agenda.desafio.src.main.domain.entity.contato.exception.IllegalContatoException;
import com.agenda.desafio.src.main.dto.contato.DadosCadastroContato;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity(name = "Contato")
@Table(name = "contatos")
public class Contato {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	private String valor;
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
	public Contato(@Valid DadosCadastroContato dados) {
		setValor(dados.valor());
		setObservacao(dados.observacao());
	}

	public Contato() {	
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
	    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"; 
	    String telefoneRegex = "^\\(\\d{2}\\)\\s?9?\\d{4}-?\\d{4}$"; 

	    if (valor.matches(telefoneRegex)) {
		    this.valor = valor.trim();
		    this.tipo = Tipo.TELEFONE;
		    return;
	    }
	    else if (valor.matches(emailRegex)) {
			this.valor = valor.trim();
			this.tipo = Tipo.EMAIL;
		    return;
		} 
	   
		throw new IllegalContatoException("Seu valor " + valor + " não corresponde a Email ou Telefone");
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public void setCliente(Long idClinte) {
		this.cliente = new Cliente();
		cliente.setId(idClinte);
	} 
}
