package com.primeirodesafio.alura.primeiro_desafio.controller;

import com.primeirodesafio.alura.primeiro_desafio.business.UsuarioService;
import com.primeirodesafio.alura.primeiro_desafio.dtos.request.UsuarioRequest;
import com.primeirodesafio.alura.primeiro_desafio.dtos.response.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    // injetando a classe service
    private final UsuarioService service;

    // criando o metodo POST para criar um usuario
    @PostMapping
    public ResponseEntity<UsuarioResponse> criarusuario(@RequestBody UsuarioRequest usuarioRequest){

        return ResponseEntity.ok( service.criarUsuario(usuarioRequest));
    }
}
