package com.agenda.desafio.src.main.dto.cliente;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroCliente(
		
		@NotNull
		String nome,
		
		@NotNull
		String cpf,
		
		LocalDate dataNascimento,
		
		String endereco
														) {
}
