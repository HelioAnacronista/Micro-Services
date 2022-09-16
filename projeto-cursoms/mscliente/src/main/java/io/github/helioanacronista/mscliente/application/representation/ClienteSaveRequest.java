package io.github.helioanacronista.mscliente.application.representation;

import io.github.helioanacronista.mscliente.domain.Cliente;
import lombok.Data;

//Esse serio o DTO

@Data
public class ClienteSaveRequest {
    private String cpf;
    private String nome;
    private Integer idade;

    //Vai conveter o json em obj de jpa para nosso entidade Cleinte
    public Cliente toModel() {
        return new Cliente(cpf,nome,idade);
    }
}
