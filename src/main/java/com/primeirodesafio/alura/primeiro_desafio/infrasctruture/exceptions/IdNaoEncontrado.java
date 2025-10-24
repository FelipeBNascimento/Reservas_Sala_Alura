package com.primeirodesafio.alura.primeiro_desafio.infrasctruture.exceptions;

public class IdNaoEncontrado extends RuntimeException{

    public IdNaoEncontrado(String message) {
        super(message);
    }

    public IdNaoEncontrado(String message, Throwable cause) {
        super(message, cause);
    }
}
