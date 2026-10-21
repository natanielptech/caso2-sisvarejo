package br.com.bompreco.sisvarejo.fidelidade.interno;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/fidelidade/clientes")
public class FidelidadeController {
    private final ClienteFidelidadeRepositorio repositorio;

    public FidelidadeController(ClienteFidelidadeRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public List<ClienteFidelidade> listar() {
        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteFidelidade> buscar(@PathVariable Long id) {
        return repositorio.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClienteFidelidade criar(@RequestBody ClienteFidelidade cliente) {
        cliente.setId(null);
        return repositorio.save(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteFidelidade> atualizar(
            @PathVariable Long id, @RequestBody ClienteFidelidade cliente) {
        if (!repositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(id);
        return ResponseEntity.ok(repositorio.save(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!repositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
