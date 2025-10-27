package com.primeirodesafio.alura.primeiro_desafio.infrastructure.exceptions;

public class IdNaoEncontrado extends RuntimeException{

    public IdNaoEncontrado(String message) {
        super(message);
    }

    public IdNaoEncontrado(String message, Throwable cause) {
        super(message, cause);
    }
}
