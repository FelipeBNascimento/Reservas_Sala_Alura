package com.primeirodesafio.alura.primeiro_desafio.dtos.response;

import lombok.*;

// Estou usando biblioteca lombok para facilitar as criações de getters setters e construtotes
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class UsuarioResponse {

    // criei os somente os atributos para retorno
    private String nome;
    private String cpf;
    private String email;
}
