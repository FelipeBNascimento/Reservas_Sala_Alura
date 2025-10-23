package com.primeirodesafio.alura.primeiro_desafio.infrasctruture.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

// Estou usando biblioteca lombok para facilitar as criações de getters setters e construtotes
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// Estou mostrando ao JPa/Hibernate que essa classe representa uma tabela no banco de dados
@Entity
//Definindo o nome da tabela
@Table(name = "reserva")
public class ReservaEntity {

    // Gerando o id automático para tabela Sala
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Criando uma coluna para data do inicio da reserva
    @Column (name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    // Passando a capacidade de pessoa que ira utilizar a sala
    @Column(name = "quantidade_pessoas")
    private Integer capacidade_pessoas;

    //Criando uma coluna para data final da reserva
    @Column(name = "dataFim", nullable = false)
    private LocalDate dataFinal;


    //Craindo uma coluna para ver o id do usuario quando reserva a sala
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;


    //Criando uma coluna com o id da sala reservada
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sala_id", nullable = false)
    private SalaEntity sala;


}
