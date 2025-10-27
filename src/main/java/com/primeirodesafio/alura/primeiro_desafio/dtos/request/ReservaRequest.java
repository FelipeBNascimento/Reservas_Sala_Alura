package com.primeirodesafio.alura.primeiro_desafio.dtos.request;

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
public class ReservaRequest {

    // criei atributos que precisa informar para o cadastro
    private Long id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFinal;
    private Integer capacidade_pessoas;
    private StatusReserva status = StatusReserva.VAZIA;
    private UsuarioEntity usuario;
    private SalaEntity sala;

    // esse atributo precisa informar para buscar o id do usuario na classe service
    private Long usuarioId;
    // esse atributo precisa informar para buscar o oid da sala na classe service
    private Long salaId;
}
