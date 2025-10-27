package com.primeirodesafio.alura.primeiro_desafio.business;

import com.primeirodesafio.alura.primeiro_desafio.dtos.ConverterDtos;
import com.primeirodesafio.alura.primeiro_desafio.dtos.request.UsuarioRequest;
import com.primeirodesafio.alura.primeiro_desafio.dtos.response.UsuarioResponse;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.entity.UsuarioEntity;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ConverterDtos converterDtos;

    // metodo para criar um usuario
    public UsuarioResponse criarUsuario(UsuarioRequest usuarioRequest){

        // convertendo o usuario para uma entity para poder salvar no banco
        UsuarioEntity usuarioParaSalvar = converterDtos.paraUsuarioEntity(usuarioRequest);

        // salvando o usuario e ja fazendo a conversão para uma classe response para fazer o retorno
        return converterDtos.paraUsuarioResponse( usuarioRepository.save(usuarioParaSalvar));
    }
}
