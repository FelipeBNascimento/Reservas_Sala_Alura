package com.primeirodesafio.alura.primeiro_desafio.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

// Estou usando biblioteca lombok para facilitar as criações de getters setters e construtotes
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Estou mostrando ao JPa/Hibernate que essa classe representa uma tabela no banco de dados
@Entity
//Definindo o nome da tabela
@Table(name = "usuarios")
public class UsuarioEntity {

    // Gerando o id automático para tabela Sala
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Criando uma coluna nome com o nome nome para preceber nome do usuario
    @Column(name = "nome", nullable = false)
    private String nome;

    // Criando uma coluna com nome cpf para passar cpf do usuario
    @Column(name = "cpf", nullable = false)
    private String cpf;

    // Criando uma coluna com nome email para receber o email do usuario
    @Column(name = "email", nullable = false)
    private String email;
}
