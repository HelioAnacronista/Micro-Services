package io.github.helioanacronista.mscliente.application;

import io.github.helioanacronista.mscliente.domain.Cliente;
import io.github.helioanacronista.mscliente.infra.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    @Transactional
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    public Optional<Cliente> getByCPF (String cpf) {
        return repository.findByCpf(cpf);
    }
}
