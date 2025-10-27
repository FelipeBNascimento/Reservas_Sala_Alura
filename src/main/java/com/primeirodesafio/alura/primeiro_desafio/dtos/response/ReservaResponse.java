package com.primeirodesafio.alura.primeiro_desafio.dtos.response;

import com.primeirodesafio.alura.primeiro_desafio.infrastructure.entity.SalaEntity;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.entity.UsuarioEntity;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.enums.StatusReserva;
import lombok.*;

import java.time.LocalDateTime;

// Estou usando biblioteca lombok para facilitar as criações de getters setters e construtotes
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaResponse {

    // criei os somente os atributos para retorno
    private LocalDateTime dataInicio;
    private LocalDateTime dataFinal;
    private Integer capacidade_pessoas;
    private StatusReserva status = StatusReserva.VAZIA;
    private UsuarioEntity usuario;
    private SalaEntity sala;
}
