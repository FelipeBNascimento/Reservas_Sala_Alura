package com.primeirodesafio.alura.primeiro_desafio.infrastructure.exceptions;

public class CapacidadeInsuficiente extends RuntimeException{

    public CapacidadeInsuficiente(String message) {
        super(message);
    }

    public CapacidadeInsuficiente(String message, Throwable cause) {
        super(message, cause);
    }
}
