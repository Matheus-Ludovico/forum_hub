package ludovico.matheus.forum_hub.controller;

import ludovico.matheus.forum_hub.dto.TopicoRequest;
import ludovico.matheus.forum_hub.dto.TopicoUpdateRequest;
import ludovico.matheus.forum_hub.model.Topico;
import ludovico.matheus.forum_hub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    // Listar todos os tópicos
    @GetMapping
    public ResponseEntity<List<Topico>> listar() {
        List<Topico> topicos = topicoService.listarTodos();
        return ResponseEntity.ok(topicos);
    }

    // Detalhar um tópico específico
    @GetMapping("/{id}")
    public ResponseEntity<Topico> detalhar(@PathVariable Long id) {
        Topico topico = topicoService.buscarPorId(id);
        return ResponseEntity.ok(topico);
    }

    // Criar um novo tópico
    @PostMapping
    public ResponseEntity<Topico> criar(@Valid @RequestBody TopicoRequest request) {
        Topico topico = topicoService.criar(request);
        return ResponseEntity.status(201).body(topico);
    }

    // Atualizar um tópico
    @PutMapping("/{id}")
    public ResponseEntity<Topico> atualizar(@PathVariable Long id, @Valid @RequestBody TopicoUpdateRequest request) {
        Topico topicoAtualizado = topicoService.atualizar(id, request);
        return ResponseEntity.ok(topicoAtualizado);
    }

    // Excluir um tópico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        topicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
