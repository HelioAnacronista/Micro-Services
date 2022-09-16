package io.github.helioanacronista.mscliente.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clientes")
public class ClientesController {

    @GetMapping
    public String status() {
        return "ok";
    }

}