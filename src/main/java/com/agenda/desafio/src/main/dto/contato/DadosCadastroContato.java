package com.agenda.desafio.src.main.dto.contato;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroContato(
		
		@NotNull
		String cpf,
		
		@NotNull
		String valor,
		
		String observacao
		
		) {
}
