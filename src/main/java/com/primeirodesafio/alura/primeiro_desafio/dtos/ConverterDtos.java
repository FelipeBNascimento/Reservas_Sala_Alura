package com.primeirodesafio.alura.primeiro_desafio.dtos;

import com.primeirodesafio.alura.primeiro_desafio.dtos.request.ReservaRequest;
import com.primeirodesafio.alura.primeiro_desafio.dtos.request.SalaRequest;
import com.primeirodesafio.alura.primeiro_desafio.dtos.request.UsuarioRequest;
import com.primeirodesafio.alura.primeiro_desafio.dtos.response.ReservaResponse;
import com.primeirodesafio.alura.primeiro_desafio.dtos.response.SalaResponse;
import com.primeirodesafio.alura.primeiro_desafio.dtos.response.UsuarioResponse;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.entity.ReservaEntity;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.entity.SalaEntity;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class ConverterDtos {


    // Criando um metodo para converter uma classe  Usuario request para Usuario entity usando o builder
    public UsuarioEntity paraUsuarioEntity(UsuarioRequest usuarioRequest) {

        UsuarioEntity usuarioConvertido = UsuarioEntity.builder()

                .id(usuarioRequest.getId())
                .nome(usuarioRequest.getNome())
                .cpf(usuarioRequest.getCpf())
                .email(usuarioRequest.getEmail())
                .build();

        return usuarioConvertido;
    }

    // Criando um metodo para converter uma classe Usuario entity para  Usuario response
    public UsuarioResponse paraUsuarioResponse(UsuarioEntity usuarioEntity) {

        UsuarioResponse usuarioConvertido = new UsuarioResponse();
        usuarioConvertido.setNome(usuarioEntity.getNome());
        usuarioConvertido.setCpf(usuarioEntity.getCpf());
        usuarioConvertido.setEmail(usuarioEntity.getEmail());

        return usuarioConvertido;
    }

    // Criando um metodo para converter uma classe  Sala request para sala entity
    public SalaEntity paraSalaEntity(SalaRequest salaRequest) {

        SalaEntity salaConvertida = new SalaEntity();

        salaConvertida.setId(salaRequest.getId());
        salaConvertida.setCapacidade(salaRequest.getCapacidade());
        salaConvertida.setAtiva(salaRequest.isAtiva());

        return salaConvertida;
    }

    // Criando um metodo para converter uma classe Sala entity para  Sala response
    public SalaResponse paraSalaResponse(SalaEntity salaEntity) {

        SalaResponse salaConvertida = new SalaResponse();
        salaConvertida.setCapacidade(salaEntity.getCapacidade());
        salaConvertida.setAtiva(salaEntity.isAtiva());

        return salaConvertida;
    }

    // Criando um metodo para converter uma classe  Reserva request para Reserva entity
    public ReservaEntity paraReservaEntity(ReservaRequest reservaRequest) {

        ReservaEntity reservaConvertida = new ReservaEntity();

        reservaConvertida.setId(reservaRequest.getId());
        reservaConvertida.setDataInicio(reservaRequest.getDataInicio());
        reservaConvertida.setDataFinal(reservaRequest.getDataFinal());
        reservaConvertida.setCapacidade_pessoas(reservaRequest.getCapacidade_pessoas());
        reservaConvertida.setStatus(reservaRequest.getStatus());
        reservaConvertida.setUsuario(reservaRequest.getUsuario());
        reservaConvertida.setSala(reservaRequest.getSala());

        return reservaConvertida;

    }
    // Criando um metodo para converter uma classe Reserva entity para  Reserva response
    public ReservaResponse paraReservaResponse(ReservaEntity reservaEntity) {

        ReservaResponse reservaConvertida = new ReservaResponse();

        reservaConvertida.setDataInicio(reservaEntity.getDataInicio());
        reservaConvertida.setDataFinal(reservaEntity.getDataFinal());
        reservaConvertida.setCapacidade_pessoas(reservaEntity.getCapacidade_pessoas());
        reservaConvertida.setStatus(reservaEntity.getStatus());
        reservaConvertida.setUsuario(reservaEntity.getUsuario());
        reservaConvertida.setSala(reservaEntity.getSala());

        return reservaConvertida;

    }

}
