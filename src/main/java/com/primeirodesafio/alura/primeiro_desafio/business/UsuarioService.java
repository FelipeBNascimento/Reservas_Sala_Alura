package com.primeirodesafio.alura.primeiro_desafio.business;

import com.primeirodesafio.alura.primeiro_desafio.dtos.ConverterDtos;
import com.primeirodesafio.alura.primeiro_desafio.dtos.request.UsuarioRequest;
import com.primeirodesafio.alura.primeiro_desafio.dtos.response.UsuarioResponse;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.entity.UsuarioEntity;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.exceptions.IdNaoEncontrado;
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

    // metodo para buscar usuario pelo id
    public UsuarioResponse buscarusauriopeloId(Long id){

        // Bunscando usuario no banco de dados
        UsuarioEntity usuarioBanco = usuarioRepository.findById(id).orElseThrow(
                ()-> new IdNaoEncontrado("id não existe" + id)
        );

        // Fazendo a converão do usuario e o retorno do metodo
        return converterDtos.paraUsuarioResponse(usuarioBanco);
    }

    //Criando o metodo de deletar usuario pelo Id
    public void apagarUsuarioPeloId(Long id){

        usuarioRepository.deleteById(id);
    }

    // Criando metodo para atualizar informações do usuario buscando usuario no banco pelo id

    public UsuarioResponse atualizarUsuario(Long id, UsuarioRequest usuarioRequest){

        // Buscando usuario no banco pelo id
        UsuarioEntity usuarioBanco = usuarioRepository.findById(id).orElseThrow(
                () -> new IdNaoEncontrado("Id não encontrado" + id)
        );
        //Atualizando usuario pelo metodo atualizarUsuario
         UsuarioEntity usuarioAtualizado = converterDtos.atualizarUsuario(usuarioRequest, usuarioBanco);

         // salvo o usuario atualizado no banco de dados
         UsuarioEntity usuarioSalvo = usuarioRepository.save(usuarioAtualizado);

         //Convertendo o usuario atualizado e ja fazendo o retorno
         return converterDtos.paraUsuarioResponse(usuarioSalvo);
    }




}
