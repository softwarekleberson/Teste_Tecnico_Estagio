package com.agenda.desafio.src.main.domain.entity.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agenda.desafio.src.main.dto.cliente.DadosDetalhamentoCliente;
import com.agenda.desafio.src.main.dto.cliente.DadosUpateCliente;

public interface RepositorioCliente {

	Cliente salvar(Cliente cliente);
	Cliente alterar(Long id, DadosUpateCliente dados);
	void deletar(Long id);
	Page<DadosDetalhamentoCliente> listar(Pageable pageable);
	DadosDetalhamentoCliente buscarPorId(Long id);
	DadosDetalhamentoCliente buscarPorCpf(String cpf);
	Page<DadosDetalhamentoCliente> buscarPorNome(Pageable pageable, String nome);
}
