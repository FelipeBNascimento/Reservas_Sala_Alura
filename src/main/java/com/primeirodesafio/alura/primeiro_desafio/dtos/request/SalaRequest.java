package com.primeirodesafio.alura.primeiro_desafio.dtos.request;

import lombok.*;

// Estou usando biblioteca lombok para facilitar as criações de getters setters e construtotes
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SalaRequest {

    // criei atributos que precisa informar para o cadastro
    private Long id;
    private Integer capacidade;
    private boolean ativa = true;
}
