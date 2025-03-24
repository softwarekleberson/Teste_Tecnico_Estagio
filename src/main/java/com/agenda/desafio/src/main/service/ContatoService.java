package com.agenda.desafio.src.main.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agenda.desafio.src.main.domain.entity.cliente.RepositorioCliente;
import com.agenda.desafio.src.main.domain.entity.contato.Contato;
import com.agenda.desafio.src.main.domain.entity.contato.RepositorioContato;
import com.agenda.desafio.src.main.dto.contato.DadosCadastroContato;
import com.agenda.desafio.src.main.dto.contato.DadosDetalhamentoContato;
import com.agenda.desafio.src.main.dto.contato.DadosUpateContato;

import jakarta.validation.Valid;

@Service
public class ContatoService {

	private final RepositorioContato repositorio;
	private final RepositorioCliente repositorioCliente;
	
	public ContatoService(RepositorioContato repositorio, RepositorioCliente repositorioCliente) {
		this.repositorio = repositorio;
		this.repositorioCliente = repositorioCliente;
	}
	
	public DadosDetalhamentoContato criar(@Valid DadosCadastroContato dados) {
		
		var idCliente = repositorioCliente.buscarPorCpf(dados.cpf());
		
		Contato contato = new Contato(dados);
		contato.setCliente(idCliente.id());
		repositorio.salvar(contato);
		return new DadosDetalhamentoContato(contato);
	}
	
	public void excluir(Long id) {		
		repositorio.deletar(id);
	}

	public DadosDetalhamentoContato update(DadosUpateContato dados, Long id) {
		var contato = repositorio.alterar(id, dados);
		return new DadosDetalhamentoContato(contato);
	}

	public Page<DadosDetalhamentoContato> listarTodos(Pageable pageable) {
		return repositorio.listar(pageable);
	}

	public DadosDetalhamentoContato encontrarPeloId(Long id) {
		return repositorio.buscarPeloId(id);
	}
}
