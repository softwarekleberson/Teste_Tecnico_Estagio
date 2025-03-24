package com.agenda.desafio.src.main.infra.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.agenda.desafio.src.main.domain.entity.cliente.exception.IllegalClienteException;
import com.agenda.desafio.src.main.domain.entity.contato.exception.IllegalContatoException;

import jakarta.persistence.EntityNotFoundException;

@Configuration
public class ErrorHandler {

	@ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handlerError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handlerError400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DateErrorValidat::new).toList());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handlerError400(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handlerError500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + ex.getLocalizedMessage());
    }
    
    @ExceptionHandler(IllegalClienteException.class)
    public ResponseEntity handlerErrorBusinesRule(IllegalClienteException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(IllegalContatoException.class)
    public ResponseEntity handlerErrorBusinesRule(IllegalContatoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    private record DateErrorValidat(String fild, String message) {
        public DateErrorValidat(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
