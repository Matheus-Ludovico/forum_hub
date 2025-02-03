package ludovico.matheus.forum_hub.service;

import ludovico.matheus.forum_hub.dto.ComentarioRequest;
import ludovico.matheus.forum_hub.exception.ResourceNotFoundException;
import ludovico.matheus.forum_hub.model.Comentario;
import ludovico.matheus.forum_hub.model.Topico;
import ludovico.matheus.forum_hub.repository.ComentarioRepository;
import ludovico.matheus.forum_hub.repository.TopicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final TopicoRepository topicoRepository;

    public ComentarioService(ComentarioRepository comentarioRepository, TopicoRepository topicoRepository) {
        this.comentarioRepository = comentarioRepository;
        this.topicoRepository = topicoRepository;
    }

    public List<Comentario> listarPorTopico(Long topicoId) {
        return comentarioRepository.findByTopicoId(topicoId);
    }

    public Comentario adicionarComentario(Long topicoId, ComentarioRequest request) {
        Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico não encontrado com id " + topicoId));

        Comentario comentario = Comentario.builder()
                .mensagem(request.getMensagem())
                .autor(request.getAutor())
                .topico(topico)
                .build();

        return comentarioRepository.save(comentario);
    }

    public void deletarComentario(Long comentarioId) {
        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Comentário não encontrado com id " + comentarioId));
        comentarioRepository.delete(comentario);
    }

    // Método para buscar um comentário pelo seu id
    public Comentario buscarPorId(Long comentarioId) {
        return comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Comentário não encontrado com id " + comentarioId));
    }
}
