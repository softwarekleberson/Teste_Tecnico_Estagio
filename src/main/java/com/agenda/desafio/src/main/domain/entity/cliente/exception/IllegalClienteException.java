package com.agenda.desafio.src.main.domain.entity.cliente.exception;

public class IllegalClienteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IllegalClienteException(String message) {
		super(message);
	}
}
