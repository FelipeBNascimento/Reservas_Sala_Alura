package com.primeirodesafio.alura.primeiro_desafio.controller;

import com.primeirodesafio.alura.primeiro_desafio.business.UsuarioService;
import com.primeirodesafio.alura.primeiro_desafio.dtos.request.UsuarioRequest;
import com.primeirodesafio.alura.primeiro_desafio.dtos.response.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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

    // Crinado metodo GET para visualizar pelo id o usuario
    @GetMapping
    public ResponseEntity<UsuarioResponse> visualizarPeloId (@RequestParam("id") Long id){

        return ResponseEntity.ok(service.buscarusauriopeloId(id));
    }

    //Criando um metodo DELETE para apagar pelo id
    @DeleteMapping
    public ResponseEntity<Void> apagarPeloId(@RequestParam("id") Long id){

        service.apagarUsuarioPeloId(id);
        return ResponseEntity.ok().build();
    }

    // Criando um metodo PUT para atualizar o usuario
    @PutMapping
    public ResponseEntity<UsuarioResponse> atualizarUsuario (@RequestBody UsuarioRequest usuarioRequest,
                                                             @RequestParam("id") Long id){

        return ResponseEntity.ok(service.atualizarUsuario(id, usuarioRequest));
    }
}
