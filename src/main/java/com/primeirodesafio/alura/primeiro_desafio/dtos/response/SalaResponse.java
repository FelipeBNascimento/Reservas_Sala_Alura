package com.primeirodesafio.alura.primeiro_desafio.dtos.response;

import lombok.*;

// Estou usando biblioteca lombok para facilitar as criações de getters setters e construtotes
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SalaResponse {

    // criei os somente os atributos para retorno
    private Integer capacidade;
    private boolean ativa = true;
}

