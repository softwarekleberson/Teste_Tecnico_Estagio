package com.agenda.desafio.src.main.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agenda.desafio.src.main.domain.entity.cliente.Cliente;
import com.agenda.desafio.src.main.domain.entity.cliente.RepositorioCliente;
import com.agenda.desafio.src.main.dto.cliente.DadosCadastroCliente;
import com.agenda.desafio.src.main.dto.cliente.DadosDetalhamentoCliente;
import com.agenda.desafio.src.main.dto.cliente.DadosUpateCliente;

import jakarta.validation.Valid;

@Service
public class ClienteService {

	private final RepositorioCliente repositorio;
	
	public ClienteService(RepositorioCliente repositorio) {
		this.repositorio = repositorio;
	}
	
	public DadosDetalhamentoCliente criar(@Valid DadosCadastroCliente dados) {
		
		Cliente cliente = new Cliente(dados);
		repositorio.salvar(cliente);
		return new DadosDetalhamentoCliente(cliente);
	}
	
	public void excluir(Long id) {		
		repositorio.deletar(id);
	}

	public DadosDetalhamentoCliente update(DadosUpateCliente dados, Long id) {
		var cliente = repositorio.alterar(id, dados);
		return new DadosDetalhamentoCliente(cliente);
	}

	public Page<DadosDetalhamentoCliente> listarTodos(Pageable pageable) {
		return repositorio.listar(pageable);
	}
	
	public DadosDetalhamentoCliente buscarPorId(Long id) {
		return repositorio.buscarPorId(id);
	}

	public Page<DadosDetalhamentoCliente> buscarPorNome(Pageable pageable, String parametro) {
	   return repositorio.buscarPorNome(pageable, parametro);
		
	}
	
	public DadosDetalhamentoCliente buscarPorCpf(String cpf) {
		return repositorio.buscarPorCpf(cpf);
	}
}
