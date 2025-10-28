package com.primeirodesafio.alura.primeiro_desafio.business;


import com.primeirodesafio.alura.primeiro_desafio.dtos.ConverterDtos;
import com.primeirodesafio.alura.primeiro_desafio.dtos.request.SalaRequest;
import com.primeirodesafio.alura.primeiro_desafio.dtos.response.SalaResponse;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.entity.SalaEntity;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.exceptions.IdNaoEncontrado;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.repository.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaService {

    private final SalaRepository repository;
    private final ConverterDtos converterDtos;

    // Criando um metodo para salva a sala
    public SalaResponse criarSala(SalaRequest salaRequest) {

        // convertendo a sala para uma entity para poder salvar no banco
        SalaEntity salaParaSalvar = converterDtos.paraSalaEntity(salaRequest);

        // salvando a sala e ja fazendo a conversão para uma classe response para fazer o retorno
        return converterDtos.paraSalaResponse(repository.save(salaParaSalvar));
    }

    // Criando um metodo para visualizar todas as salas
    public List<SalaResponse> visualizarSalas() {

        // Criando uma lista de entitys buscando pelo repository todas as listas no banco
        List<SalaEntity> salas = repository.findAll();

        // convertendo a lista entity para uma lista response e fazendo o retorno
        return converterDtos.paraListasResponses(salas);

    }

    // Criando um metodo para visualizar a sala pelo id
    public SalaResponse visualizarSalaPeloId(Long id) {

        // Buscando a sala no banco
        SalaEntity sala = repository.findById(id).orElseThrow(
                () -> new IdNaoEncontrado("Id não encontrado" + id)
        );

        // Fazendo a conversão da sala e ja fazendo o retorno
        return converterDtos.paraSalaResponse(sala);
    }

    // Criando um metodo para deletar sala pelo id
    public void apagarSalapeloId(Long id) {

        repository.deleteById(id);
    }

    // Criando um metodo para atualizar sala pelo id
    public SalaResponse atualizarSalaPeloId(Long id, SalaRequest salaRequest) {

        // Primeiramente busco a sala no banco de dados
        SalaEntity salaNoBanco = repository.findById(id).orElseThrow(
                () -> new IdNaoEncontrado("Id não encontrado" + id)
        );

        // Atualizo a sala com metodo atualizar sala
        SalaEntity salaAtualizada = converterDtos.atualizarSala(salaRequest, salaNoBanco);

        // salvo a sala atualizada
        SalaEntity salaSalva = repository.save(salaAtualizada);

        // Converto a sala atualizada para uma sala response
        SalaResponse salaResponse = converterDtos.paraSalaResponse(salaSalva);

        // faço o retorno da sala
        return salaResponse;
    }


}
