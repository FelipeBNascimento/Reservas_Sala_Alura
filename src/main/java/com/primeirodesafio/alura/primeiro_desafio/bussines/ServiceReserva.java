package com.primeirodesafio.alura.primeiro_desafio.bussines;

import com.primeirodesafio.alura.primeiro_desafio.infrasctruture.entity.ReservaEntity;
import com.primeirodesafio.alura.primeiro_desafio.infrasctruture.entity.SalaEntity;
import com.primeirodesafio.alura.primeiro_desafio.infrasctruture.entity.UsuarioEntity;
import com.primeirodesafio.alura.primeiro_desafio.infrasctruture.exceptions.SalaReservada;
import com.primeirodesafio.alura.primeiro_desafio.infrasctruture.repository.ReservaRepository;
import com.primeirodesafio.alura.primeiro_desafio.infrasctruture.repository.SalaRepository;
import com.primeirodesafio.alura.primeiro_desafio.infrasctruture.repository.UsuarioRepositoy;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ServiceReserva {

    private final ReservaRepository reservaRepository;
    private final SalaRepository salaRepository;
    private final UsuarioRepositoy usuarioRepositoy;


    public ReservaEntity reservarSala(ReservaEntity reserva, SalaEntity sala, UsuarioEntity usuario) {

        // Busquei a sala no banco de dados
        SalaRepository salaNoBanco = salaRepository.findById(sala.getId()).orElseThrow();

        // Busquei o usuario no banco de dados
        UsuarioEntity usuariobanco = usuarioRepositoy.findById(usuario.getId()).orElseThrow();

        // primeira regra não reserva sala inativa
        if (!sala.isAtiva()) {
            throw new SalaReservada("A sala está inativa e não pode ser reservada");
        }

        // Adicionando o meto validação de datas
        validarDatas(reserva.getDataInicio(), reserva.getDataFinal());

        // Adicionando a validação da capacidade da sala
        if (sala.getCapacidade() < reserva.getCapacidade_pessoas()) {

            throw new RuntimeException("Capacidade da sala inferio a quantidade de pessoas");
        }

        // Adicionando a validação de que a sala não pode ser negativa
        if (sala.getCapacidade() >= 0) {

            throw new RuntimeException("Capacidade de sala não pode ser negativa");
        }


        // Adicionando uma reserva ao anco de dados depois de todas as validações acima
        ReservaEntity novaReserva = new ReservaEntity();
        novaReserva.setSala(sala);
        novaReserva.setUsuario(usuario);
        novaReserva.setDataInicio(reserva.getDataInicio());
        novaReserva.setDataFinal(reserva.getDataFinal());

        // Retornando a sala reservada
        return reservaRepository.save(novaReserva);


    }

    // criando um metodo para validação das datas
    public void validarDatas(LocalDate datainicio, LocalDate dataFim) {

        // Não pode passar a datas em branco
        if (datainicio == null || dataFim == null) {

            throw new RuntimeException("As datas são obrigatorias tanto inicio como fim");
        }

        // A data fim sempre deve estar no mesmo dia ou depois da data inicial
        if (datainicio.isAfter(dataFim)) {

            throw new RuntimeException("A data inicial deve ser antes ou no mesmo dia que a data final");
        }

        // Tanto  a data final como  a dtaa inicial não pode ser antes da data atual
        if (dataFim.isBefore(LocalDate.now()) || (datainicio.isBefore(LocalDate.now()))) {

            throw new RuntimeException(" A data final não pode ser antes que o dia da reserva");
        }

    }

}
