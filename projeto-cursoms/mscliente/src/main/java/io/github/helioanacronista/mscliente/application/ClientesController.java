package io.github.helioanacronista.mscliente.application;

import io.github.helioanacronista.mscliente.application.representation.ClienteSaveRequest;
import io.github.helioanacronista.mscliente.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clientes")
@Slf4j
@RequiredArgsConstructor
public class ClientesController {

    private final ClienteService service;

    @GetMapping
    public String status(){
        log.info("Obtendo o status do microservice de clientes");
        return "ok";
    }

    //Salvar cliente
    @PostMapping
    public ResponseEntity save(@RequestBody ClienteSaveRequest request){
        var cliente = request.toModel();
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

    //Buscar pelo cpf e verifica se existe
    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf){
        var cliente = service.getByCPF(cpf);
        if(cliente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }
}
