package com.agenda.desafio.src.main.domain.entity.contato.exception;

public class IllegalContatoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IllegalContatoException(String message) {
		super(message);
	}
}
