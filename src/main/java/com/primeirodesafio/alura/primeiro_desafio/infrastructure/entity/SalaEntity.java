package com.primeirodesafio.alura.primeiro_desafio.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

// Estou usando biblioteca lombok para facilitar as criações de getters setters e construtotes
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// Estou mostrando ao JPa/Hibernate que essa classe representa uma tabela no banco de dados
@Entity
//Definindo o nome da tabela
@Table(name = "sala")
public class SalaEntity {


    // Gerando o id automático para tabela Sala
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Definindo uma coluna com a capacidade da sala
    @Column(name = "capacidade")
    private Integer capacidade;

    //Coluna que define se a sala esta ativa ou inativa
    @Column(name = "ativa")
    private boolean ativa = true;


}
