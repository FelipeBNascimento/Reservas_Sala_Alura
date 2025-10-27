package com.primeirodesafio.alura.primeiro_desafio.controller;

import com.primeirodesafio.alura.primeiro_desafio.business.ServiceReserva;
import com.primeirodesafio.alura.primeiro_desafio.dtos.request.ReservaRequest;
import com.primeirodesafio.alura.primeiro_desafio.dtos.request.SalaRequest;
import com.primeirodesafio.alura.primeiro_desafio.dtos.request.UsuarioRequest;
import com.primeirodesafio.alura.primeiro_desafio.dtos.response.ReservaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reserva")
public class ReservaController {


    // injetando a classe service
    private final ServiceReserva serviceReserva;


    // criando o metodo POST para criar uma reserva
    @PostMapping
    public ResponseEntity<ReservaResponse> criarReserva(@RequestBody ReservaRequest reservaRequest){

        return ResponseEntity.ok(serviceReserva.fazerReserva(reservaRequest));
    }
}
