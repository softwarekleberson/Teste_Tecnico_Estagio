package com.agenda.desafio.src.main.dto.cliente;

import java.time.LocalDate;

public record DadosUpateCliente(
		
		String nome,
		LocalDate dataNascimento,
		String endereco
		
		) {
}
