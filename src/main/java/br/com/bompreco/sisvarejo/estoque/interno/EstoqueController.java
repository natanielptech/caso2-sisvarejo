package br.com.bompreco.sisvarejo.estoque.interno;

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
@RequestMapping("/v1/estoque")
public class EstoqueController {
    private final ItemEstoqueRepositorio repositorio;

    public EstoqueController(ItemEstoqueRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public List<ItemEstoque> listar() {
        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemEstoque> buscar(@PathVariable Long id) {
        return repositorio.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ItemEstoque criar(@RequestBody ItemEstoque item) {
        item.setId(null);
        return repositorio.save(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemEstoque> atualizar(@PathVariable Long id, @RequestBody ItemEstoque item) {
        if (!repositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        item.setId(id);
        return ResponseEntity.ok(repositorio.save(item));
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
