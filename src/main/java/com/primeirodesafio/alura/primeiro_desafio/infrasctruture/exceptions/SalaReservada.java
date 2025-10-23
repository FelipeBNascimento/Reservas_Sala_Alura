package com.primeirodesafio.alura.primeiro_desafio.infrasctruture.exceptions;

public class SalaReservada extends RuntimeException{

    public SalaReservada(String message) {
        super(message);
    }

    public SalaReservada(String message, Throwable cause) {
        super(message, cause);
    }
}
