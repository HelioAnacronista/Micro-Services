package io.github.helioanacronista.mscartoes.application;

import io.github.helioanacronista.mscartoes.application.DTO.CartaoSaveRequest;
import io.github.helioanacronista.mscartoes.domain.Cartao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class cartoesController {

    private final CartaoService service;

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity cadatra (@RequestBody CartaoSaveRequest request) {
        Cartao cartao = request.toModel();
        service.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    ///cartoes?renda=5000, ele vai cair aqui. se nao cair no getStatus.
    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAteh (@RequestParam("renda") Long renda) {
        List<Cartao> list = service.getCartoesRendaMenorIgaul(renda);
        return ResponseEntity.ok(list);
    }
}
