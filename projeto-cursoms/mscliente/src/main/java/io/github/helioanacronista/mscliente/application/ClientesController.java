package io.github.helioanacronista.mscliente.application;

import io.github.helioanacronista.mscliente.application.representation.ClienteSaveRequest;
import io.github.helioanacronista.mscliente.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClientesController {

    private final ClienteService service;

    @GetMapping
    public String status() {
        return "ok";
    }

    //Salvar cliente
    public ResponseEntity save (@RequestBody ClienteSaveRequest request) {
        Cliente cliente = request.toModel();
        service.save(cliente);

        /*
        Criar uma Url corrente http//localhost:PORT/clientes?cpf=*********
        Assim quando fazer get nessa url ele obter os dados do cliente
         */
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();

        return ResponseEntity.created(headerLocation).build();
    }
}
