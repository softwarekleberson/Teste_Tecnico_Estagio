package com.agenda.desafio.src.main.dto.contato;

import com.agenda.desafio.src.main.domain.entity.contato.Contato;
import com.agenda.desafio.src.main.domain.entity.contato.Tipo;

public record DadosDetalhamentoContato(Long id, Tipo tipo, String valor, String observacao) {
	public DadosDetalhamentoContato(Contato contato) {
		this(contato.getId(), contato.getTipo(), contato.getValor(), contato.getObservacao());
	}
}
