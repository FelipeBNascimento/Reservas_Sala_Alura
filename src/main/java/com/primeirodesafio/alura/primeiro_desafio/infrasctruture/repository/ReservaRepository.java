package com.primeirodesafio.alura.primeiro_desafio.infrasctruture.repository;

import com.primeirodesafio.alura.primeiro_desafio.infrasctruture.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Estou mostrando ao JPa/Hibernate que essa interface é uma camada de persistencia
// e extend com a classe JpaRepository porque essa classe ja tem alguns metodos pré-definidos
// para fazermos operações CRUD
@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
}
