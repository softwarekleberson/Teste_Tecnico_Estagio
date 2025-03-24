package com.agenda.desafio.src.main.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.agenda.desafio.src.main.domain.entity.cliente.Cliente;
import com.agenda.desafio.src.main.domain.entity.cliente.RepositorioCliente;
import com.agenda.desafio.src.main.dto.cliente.DadosDetalhamentoCliente;
import com.agenda.desafio.src.main.dto.cliente.DadosUpateCliente;
import com.agenda.desafio.src.main.infra.persistencia.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteDao implements RepositorioCliente {

	private final ClienteRepository repository;

	public ClienteDao(ClienteRepository repository) {
		this.repository = repository;
	}

	@Override
	public Cliente salvar(Cliente cliente) {
		return repository.save(cliente);
	}

	@Transactional
	@Override
	public Cliente alterar(Long id, DadosUpateCliente dados) {
		Cliente cliente = repository.findById(id)
	    .orElseThrow(() -> 
		new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		boolean atualiado = false;
		
		if(dados.nome() != null && !dados.nome().equals(cliente.getNome())) {
			cliente.setNome(dados.nome());
			atualiado = true;
		}
		
		if(dados.dataNascimento() != null && !dados.dataNascimento().equals(cliente.getDataNascimento())) {
			cliente.setDataNascimento(dados.dataNascimento());
			atualiado = true;
		}
		
		if(dados.endereco() != null && !dados.endereco().equals(cliente.getEndereco().getEndereco())) {
			cliente.getEndereco().setEndereco(dados.endereco());
			atualiado = true;
		}
		
		return atualiado ? repository.save(cliente) : cliente;	
	}

	@Transactional
	@Override
	public void deletar(Long id) {
		Cliente cliente = repository.findById(id)
		.orElseThrow(() 
		-> new ResponseStatusException(HttpStatus.NOT_FOUND));
	
		repository.delete(cliente);
	}

	@Override
	public Page<DadosDetalhamentoCliente> listar(Pageable pageable) {
		return repository.findAll(pageable)
			   .map(DadosDetalhamentoCliente::new);
	}
	
	@Override
	public DadosDetalhamentoCliente buscarPorId(Long id) {
		return repository.findById(id)
			   .map(DadosDetalhamentoCliente::new)
			   .orElseThrow(() 
			   -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@Override
	public DadosDetalhamentoCliente buscarPorCpf(String cpf) {
	    return repository.findByCpf(cpf)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado com CPF: " + cpf));
	}



	@Override
	public Page<DadosDetalhamentoCliente> buscarPorNome(Pageable pageable, String nome) {
		return repository.findByNome(pageable, nome)
				   .map(DadosDetalhamentoCliente::new);
	}
}
