package com.primeirodesafio.alura.primeiro_desafio.infrasctruture.repository;

import com.primeirodesafio.alura.primeiro_desafio.infrasctruture.entity.ReservaEntity;
import com.primeirodesafio.alura.primeiro_desafio.infrasctruture.entity.SalaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// Estou mostrando ao JPa/Hibernate que essa interface é uma camada de persistencia
// e extend com a classe JpaRepository porque essa classe ja tem alguns metodos pré-definidos
// para fazermos operações CRUD
@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {


    // Criando uma logica de reserva usado linguagem SQL que não pode reserva
    // a sala enquanto a data fim da sala estiver maior que a data inicial da nova reserva
    @Query("""
        SELECT re FROM ReservaEntity re 
        WHERE re.sala.id = :salaId
        AND re.dataFinal > :dataInicioNovareserva
        AND re.dataInicio < :dataFimNovaReserva
    """)

    Optional<ReservaEntity> findConflitoDeReserva(
            @Param("salaId") Long salaId,
            @Param("dataInicioNovareserva") LocalDateTime dataInicioNovareserva,
            @Param("dataFimNovaReserva") LocalDateTime dataFimNovaReserva
    );
}
