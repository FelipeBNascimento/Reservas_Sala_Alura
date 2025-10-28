package com.primeirodesafio.alura.primeiro_desafio.controller;

import com.primeirodesafio.alura.primeiro_desafio.business.SalaService;
import com.primeirodesafio.alura.primeiro_desafio.dtos.request.SalaRequest;
import com.primeirodesafio.alura.primeiro_desafio.dtos.response.SalaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sala")
public class SalaController {

    // injetando a classe service
    private final SalaService service;

    // criando o metodo POST para criar uma sala
    @PostMapping
    public ResponseEntity<SalaResponse> criarSala(@RequestBody SalaRequest salaRequest) {

        return ResponseEntity.ok(service.criarSala(salaRequest));
    }

    // criando metodo GET para visaulizar todas as salas
    @GetMapping
    public ResponseEntity<List<SalaResponse>> visualizarSalas() {

        return ResponseEntity.ok(service.visualizarSalas());
    }

    // Criando um metodo GET ppara visualizar somente uma sala pelo id
    @GetMapping("/id")
    public ResponseEntity<SalaResponse> visualizarSalapeloId(@RequestParam("id") Long id) {

        return ResponseEntity.ok(service.visualizarSalaPeloId(id));
    }

    // Criando um metodo DELETE para apagar a sala do banco de dados pelo id
    @DeleteMapping
    public ResponseEntity<Void> apagarPeloId (@RequestParam("id") Long id){

        service.apagarSalapeloId(id);
        return ResponseEntity.ok().build();
    }

    // Criando um metodo PUT para atualizar a sala
    @PutMapping
    public ResponseEntity<SalaResponse> atualizarSalapeloId(@RequestBody SalaRequest salaRequest,
                                                            @RequestParam("id") Long id){
        return ResponseEntity.ok(service.atualizarSalaPeloId(id, salaRequest));
    }

}
