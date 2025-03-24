package com.agenda.desafio.src.main.domain.entity.cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.agenda.desafio.src.main.domain.entity.cliente.exception.IllegalClienteException;
import com.agenda.desafio.src.main.domain.entity.contato.Contato;
import com.agenda.desafio.src.main.dto.cliente.DadosCadastroCliente;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity(name = "Cliente")
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	
	@Embedded
	private Endereco endereco;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Contato> contatos = new ArrayList<Contato>();
		
	public Cliente(@Valid DadosCadastroCliente dados) {
		setNome(dados.nome());
		setCpf(dados.cpf());
		setDataNascimento(dados.dataNascimento());
		setEndereco(dados.endereco());
	}
	
	public Cliente() {
	}
	
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
		if(nome == null || nome.isEmpty()) {
			throw new IllegalClienteException("O nome não pode ser nulo ou vazio. Por favor, forneça um nome válido.");
		}
		this.nome = nome.trim();
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		String regexCpf = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$";
		if(cpf == null || !cpf.matches(regexCpf)) {
			throw new IllegalClienteException("CPF inválido. O formato correto é: XXX.XXX.XXX-XX");
		}
		this.cpf = cpf.trim();
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
	    LocalDate dataAtual = LocalDate.now();
	    LocalDate dataMinimaPermitida = LocalDate.of(1905, 12, 12);
	    
	    if (dataNascimento != null) {
	        if (dataNascimento.isBefore(dataMinimaPermitida) || dataNascimento.isAfter(dataAtual)) {
	            throw new IllegalClienteException("A data de nascimento é inválida");
	        }
	    }
	    
	    this.dataNascimento = dataNascimento;
	}

	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = new Endereco(endereco);
	}
	
	public List<Contato> getContatos() {
		return contatos;
	}
}
