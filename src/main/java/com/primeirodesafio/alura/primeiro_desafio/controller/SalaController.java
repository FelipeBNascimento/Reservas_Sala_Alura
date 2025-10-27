package com.primeirodesafio.alura.primeiro_desafio.controller;

import com.primeirodesafio.alura.primeiro_desafio.business.SalaService;
import com.primeirodesafio.alura.primeiro_desafio.dtos.request.SalaRequest;
import com.primeirodesafio.alura.primeiro_desafio.dtos.response.SalaResponse;
import com.primeirodesafio.alura.primeiro_desafio.infrastructure.entity.ReservaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sala")
public class SalaController {

    // injetando a classe service
    private final SalaService service;

    // criando o metodo POST para criar uma sala
    @PostMapping
    public ResponseEntity<SalaResponse> criarSala(@RequestBody SalaRequest salaRequest){

        return ResponseEntity.ok( service.criarSala(salaRequest));
    }
}
