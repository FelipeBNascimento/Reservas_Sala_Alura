package com.primeirodesafio.alura.primeiro_desafio.infrasctruture.repository;

// Estou mostrando ao JPa/Hibernate que essa interface é uma camada de persistencia
// e extend com a classe JpaRepository porque essa classe ja tem alguns metodos pré-definidos
// para fazermos operações CRUD

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<SalaRepository, Long> {
}
