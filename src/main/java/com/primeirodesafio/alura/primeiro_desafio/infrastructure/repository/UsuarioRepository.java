package com.primeirodesafio.alura.primeiro_desafio.infrastructure.repository;

import com.primeirodesafio.alura.primeiro_desafio.infrastructure.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Estou mostrando ao JPa/Hibernate que essa interface é uma camada de persistencia
// e extend com a classe JpaRepository porque essa classe ja tem alguns metodos pré-definidos
// para fazermos operações CRUD
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
