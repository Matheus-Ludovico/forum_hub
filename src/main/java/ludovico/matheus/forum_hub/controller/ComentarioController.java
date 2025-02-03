package ludovico.matheus.forum_hub.controller;

import jakarta.validation.Valid;
import ludovico.matheus.forum_hub.dto.ComentarioRequest;
import ludovico.matheus.forum_hub.model.Comentario;
import ludovico.matheus.forum_hub.service.ComentarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ludovico.matheus.forum_hub.exception.ResourceNotFoundException;


import java.util.List;

@RestController
@RequestMapping("/topicos/{topicoId}/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    // Endpoint para listar comentários de um tópico
    @GetMapping
    public ResponseEntity<List<Comentario>> listar(@PathVariable Long topicoId) {
        List<Comentario> comentarios = comentarioService.listarPorTopico(topicoId);
        return ResponseEntity.ok(comentarios);
    }

    // Endpoint para adicionar um comentário a um tópico
    @PostMapping
    public ResponseEntity<Comentario> adicionar(@PathVariable Long topicoId,
                                                @Valid @RequestBody ComentarioRequest request) {
        Comentario comentario = comentarioService.adicionarComentario(topicoId, request);
        return ResponseEntity.status(201).body(comentario);
    }
    @DeleteMapping("/{comentarioId}")
    public ResponseEntity<Void> deletar(@PathVariable Long topicoId, @PathVariable Long comentarioId) {
        Comentario comentario = comentarioService.buscarPorId(comentarioId);
        if (!comentario.getTopico().getId().equals(topicoId)) {
            throw new ResourceNotFoundException("Comentário não pertence ao tópico com id " + topicoId);
        }
        comentarioService.deletarComentario(comentarioId);
        return ResponseEntity.noContent().build();
    }
}
