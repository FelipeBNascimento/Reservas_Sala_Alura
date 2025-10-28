package com.primeirodesafio.alura.primeiro_desafio.controller;

import com.primeirodesafio.alura.primeiro_desafio.business.ServiceReserva;
import com.primeirodesafio.alura.primeiro_desafio.dtos.request.ReservaRequest;
import com.primeirodesafio.alura.primeiro_desafio.dtos.request.SalaRequest;
import com.primeirodesafio.alura.primeiro_desafio.dtos.request.UsuarioRequest;
import com.primeirodesafio.alura.primeiro_desafio.dtos.response.ReservaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //Criando metodo GET para visualizar a lista de reservas pelo id da sala

    @GetMapping
    public ResponseEntity<List<ReservaResponse>> visualizarListadeSalasreservada(@RequestParam Long id){

        return ResponseEntity.ok(serviceReserva.visualizarListasPeloId(id));
    }
}
