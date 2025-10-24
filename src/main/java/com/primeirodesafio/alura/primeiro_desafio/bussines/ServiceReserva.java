package com.primeirodesafio.alura.primeiro_desafio.bussines;

import com.primeirodesafio.alura.primeiro_desafio.infrasctruture.entity.ReservaEntity;
import com.primeirodesafio.alura.primeiro_desafio.infrasctruture.entity.SalaEntity;
import com.primeirodesafio.alura.primeiro_desafio.infrasctruture.entity.UsuarioEntity;
import com.primeirodesafio.alura.primeiro_desafio.infrasctruture.enums.StatusReserva;
import com.primeirodesafio.alura.primeiro_desafio.infrasctruture.exceptions.IdNaoEncontrado;
import com.primeirodesafio.alura.primeiro_desafio.infrasctruture.exceptions.SalaReservada;
import com.primeirodesafio.alura.primeiro_desafio.infrasctruture.repository.ReservaRepository;
import com.primeirodesafio.alura.primeiro_desafio.infrasctruture.repository.SalaRepository;
import com.primeirodesafio.alura.primeiro_desafio.infrasctruture.repository.UsuarioRepositoy;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceReserva {

    private final ReservaRepository reservaRepository;
    private final SalaRepository salaRepository;
    private final UsuarioRepositoy usuarioRepositoy;


    @Transactional
    public ReservaEntity reservarSala(ReservaEntity reserva, SalaEntity sala, UsuarioEntity usuario) {

        // Busquei a sala no banco de dados
        SalaRepository salaNoBanco = salaRepository.findById(sala.getId()).orElseThrow(
                () -> new IdNaoEncontrado("Id não encontrado" + usuario.getId())
        );

        // Busquei o usuario no banco de dados
        UsuarioEntity usuariobanco = usuarioRepositoy.findById(usuario.getId()).orElseThrow(
                () -> new IdNaoEncontrado("Id não encontrado" + sala.getId())
        );

        // primeira regra não reserva sala inativa
        if (!sala.isAtiva()) {
            throw new SalaReservada("A sala está inativa e não pode ser reservada");
        }

        // Adicionando o meto validação de datas
        validarDatas(reserva.getDataInicio(), reserva.getDataFinal());

        // Adicionando a validação da capacidade da sala
        if (sala.getCapacidade() < reserva.getCapacidade_pessoas()) {

            throw new RuntimeException("Capacidade da sala inferior a quantidade de pessoas");
        }

        // Adicionando a validação de que a sala não pode ser negativa
        if (reserva.getCapacidade_pessoas() <= 0) {

            throw new RuntimeException("Tem que ter ao menos 1 pessoa para reservar a sala");
        }

        // Adicionando o metodo verificar Horarios de sala
        verificConflitoDeHorarios(reserva, reserva.getDataInicio(), reserva.getDataFinal());

        // Adicionando o metodo verificando o status
        checagemStatus(reserva);


        // Adicionando uma reserva ao anco de dados depois de todas as validações acima
        ReservaEntity novaReserva = new ReservaEntity();
        novaReserva.setSala(sala);
        novaReserva.setUsuario(usuario);
        novaReserva.setDataInicio(reserva.getDataInicio());
        novaReserva.setDataFinal(reserva.getDataFinal());
        novaReserva.setStatus(StatusReserva.ATIVA);

        // Retornando a sala reservada
        return reservaRepository.save(novaReserva);

    }

    // criando um metodo para validação das datas
    public void validarDatas(LocalDateTime datainicio, LocalDateTime dataFim) {

        // Não pode passar a datas em branco
        if (datainicio == null || dataFim == null) {
            throw new SalaReservada("As datas são obrigatorias tanto inicio como fim");
        }
        // A data fim sempre deve estar no mesmo dia ou depois da data inicial
        if (datainicio.isAfter(dataFim)) {
            throw new SalaReservada("A data inicial deve ser antes ou no mesmo dia que a data final");
        }
        // Tanto  a data final como  a dtaa inicial não pode ser antes da data atual
        if (dataFim.isBefore(LocalDateTime.now()) || (datainicio.isBefore(LocalDateTime.now()))) {
            throw new SalaReservada(" A data final não pode ser antes que o dia da reserva");
        }

    }

    // Metodo criado para resolver conflitos de tempo de reserva em datas
    public void verificConflitoDeHorarios(ReservaEntity reserva, LocalDateTime dataInicio, LocalDateTime dataFim) {

        // Buscando uma reserva verificando o id da sala e os horarios que estão reservados
        Optional<ReservaEntity> conflitoHorario = reservaRepository.findConflitoDeReserva(reserva.getSala().getId(),
                dataInicio, dataFim);


        // Fazendo a regra de negocio caso esteja reservado gerar um excessão
        // informando que a sala ja está reservada
        if (conflitoHorario.isPresent()) {
            throw new SalaReservada("A sala com id " + reserva.getSala().getId() + " ja esta com horarios reservados");
        }


    }

    public void checagemStatus(ReservaEntity reserva){
        if (reserva.getStatus() == StatusReserva.ATIVA){
            throw new SalaReservada("A sala esta reservada");
        }


    }

}

