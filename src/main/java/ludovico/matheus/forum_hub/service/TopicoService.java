package ludovico.matheus.forum_hub.service;

import ludovico.matheus.forum_hub.dto.TopicoRequest;
import ludovico.matheus.forum_hub.dto.TopicoUpdateRequest;
import ludovico.matheus.forum_hub.exception.DuplicateTopicException;
import ludovico.matheus.forum_hub.exception.ResourceNotFoundException;
import ludovico.matheus.forum_hub.model.Topico;
import ludovico.matheus.forum_hub.repository.TopicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;

    public TopicoService(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    public List<Topico> listarTodos() {
        return topicoRepository.findAll();
    }

    public Topico buscarPorId(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico não encontrado com id " + id));
    }

    public Topico criar(TopicoRequest request) {
        if (topicoRepository.existsByTituloAndMensagem(request.getTitulo(), request.getMensagem())) {
            throw new DuplicateTopicException("Já existe um tópico com o mesmo título e mensagem.");
        }

        Topico topico = Topico.builder()
                .titulo(request.getTitulo())
                .mensagem(request.getMensagem())
                .autor(request.getAutor())
                .curso(request.getCurso())
                .build();

        return topicoRepository.save(topico);
    }

    public Topico atualizar(Long id, TopicoUpdateRequest request) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico não encontrado com id " + id));

        topico.setTitulo(request.getTitulo());
        topico.setMensagem(request.getMensagem());
        topico.setCurso(request.getCurso());

        return topicoRepository.save(topico);
    }

    public void deletar(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico não encontrado com id " + id));

        topicoRepository.delete(topico);
    }
}
