package com.primeirodesafio.alura.primeiro_desafio.business;

import com.primeirodesafio.alura.primeiro_desafio.dtos.ConverterDtos;
import com.primeirodesafio.alura.primeiro_desafio.dtos.request.ReservaRequest;
import com.primeirodesafio.alura.primeiro_desafio.dtos.request.SalaRequest;
import com.primeirodesafio.alura.primeiro_desafio.dtos.request.UsuarioRequest;
import com.primeirodesafio.alura.primeiro_desafio.dtos.response.ReservaResponse;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.entity.ReservaEntity;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.entity.SalaEntity;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.entity.UsuarioEntity;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.enums.StatusReserva;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.exceptions.CapacidadeInsuficiente;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.exceptions.IdNaoEncontrado;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.exceptions.SalaReservada;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.repository.ReservaRepository;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.repository.SalaRepository;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceReserva {

    private final ReservaRepository reservaRepository;
    private final SalaRepository salaRepository;
    private final UsuarioRepository usuarioRepositoy;
    private final ConverterDtos converterDtos;


    @Transactional
    // Metodo para fazer a reserva implementando as regras de negocio
    public ReservaResponse fazerReserva(ReservaRequest reservaRequest) {

        // Busquei a sala no banco de dados pelo id que tem na reserva
        SalaEntity salaNoBanco = salaRepository.findById(reservaRequest.getSalaId())
                .orElseThrow(() -> new IdNaoEncontrado("Id não encontrado " + reservaRequest.getSalaId()));

        // Busquei o usuario no banco de dados pelo id que tem na reserva
        UsuarioEntity usuarioBanco = usuarioRepositoy.findById(reservaRequest.getUsuarioId())
                .orElseThrow(() -> new IdNaoEncontrado("Id não encontrado" + reservaRequest.getUsuarioId()));

        // primeira regra não reserva sala inativa
        if (!salaNoBanco.isAtiva()) {
            throw new SalaReservada("A sala está inativa e não pode ser reservada");
        }

        //  // Fiz a conversão da reserva request para reserva entity com metodo converter da classe converter DTOS
        ReservaEntity reservaEntity = converterDtos.paraReservaEntity(reservaRequest);

        // passei a sala buscada no banco para a reserva
        reservaEntity.setSala(salaNoBanco);

        // passei o usuario buscado no banco para a reserva
        reservaEntity.setUsuario(usuarioBanco);

        // Adicionando o meto validação de datas
        validarDatas(reservaEntity.getDataInicio(), reservaEntity.getDataFinal());

        // Adicionando a validação da capacidade da sala
        if (salaNoBanco.getCapacidade() < reservaEntity.getCapacidade_pessoas()) {

            throw new CapacidadeInsuficiente("Capacidade da sala inferior a quantidade de pessoas");
        }

        // Adicionando a validação de que a sala não pode ser negativa
        if (reservaEntity.getCapacidade_pessoas() <= 0) {

            throw new CapacidadeInsuficiente("Tem que ter ao menos 1 pessoa para reservar a sala");
        }
        // Adicionando o metodo verificar Horarios de sala
        verificConflitoDeHorarios(reservaEntity, reservaEntity.getDataInicio(), reservaEntity.getDataFinal());

        // depois de toda verificação informo quea sala esta ativa
        reservaEntity.setStatus(StatusReserva.ATIVA);

        // Convertendo a reserva entity para response e ja fazendo o retorno a sala reservada
        return converterDtos.paraReservaResponse(reservaRepository.save(reservaEntity));
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

    // Criando metodo para visualizar as reservas pelo id
    public List<ReservaResponse> visualizarListasPeloId (Long id){

        List<ReservaEntity> reservas =  reservaRepository.findBySalaId(id);

        List<ReservaResponse> listas = converterDtos.listasDeReservasResponse(reservas);
        return listas;

    }

    // criando um metodo para apagar a reserva pelo id
    public void apagarPeloId(Long id){

        reservaRepository.deleteById(id);
    }




}


