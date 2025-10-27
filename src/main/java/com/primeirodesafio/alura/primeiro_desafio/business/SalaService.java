package com.primeirodesafio.alura.primeiro_desafio.business;


import com.primeirodesafio.alura.primeiro_desafio.dtos.ConverterDtos;
import com.primeirodesafio.alura.primeiro_desafio.dtos.request.SalaRequest;
import com.primeirodesafio.alura.primeiro_desafio.dtos.response.SalaResponse;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.entity.SalaEntity;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.repository.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalaService {

    private final SalaRepository repository;
    private final ConverterDtos converterDtos;

    // Criando um metodo para salva a sala
    public SalaResponse criarSala(SalaRequest salaRequest){

        // convertendo a sala para uma entity para poder salvar no banco
        SalaEntity salaParaSalvar = converterDtos.paraSalaEntity(salaRequest);

        // salvando a sala e ja fazendo a conversão para uma classe response para fazer o retorno
        return converterDtos.paraSalaResponse(repository.save(salaParaSalvar));
    }


}
