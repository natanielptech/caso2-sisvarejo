package br.com.bompreco.sisvarejo.fiscal.interno;

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
@RequestMapping("/v1/fiscal/documentos")
public class FiscalController {
    private final DocumentoFiscalRepositorio repositorio;

    public FiscalController(DocumentoFiscalRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public List<DocumentoFiscal> listar() {
        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoFiscal> buscar(@PathVariable Long id) {
        return repositorio.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DocumentoFiscal criar(@RequestBody DocumentoFiscal documento) {
        documento.setId(null);
        return repositorio.save(documento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentoFiscal> atualizar(@PathVariable Long id, @RequestBody DocumentoFiscal documento) {
        if (!repositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        documento.setId(id);
        return ResponseEntity.ok(repositorio.save(documento));
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
