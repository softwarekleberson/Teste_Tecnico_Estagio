package com.agenda.desafio.src.main.domain.entity.contato;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agenda.desafio.src.main.dto.contato.DadosDetalhamentoContato;
import com.agenda.desafio.src.main.dto.contato.DadosUpateContato;

public interface RepositorioContato {

	Contato salvar(Contato contato);
	Contato alterar(Long id, DadosUpateContato dados);
	void deletar(Long id);
	Page<DadosDetalhamentoContato> listar(Pageable pageable);
	DadosDetalhamentoContato buscarPeloId(Long id);
}
