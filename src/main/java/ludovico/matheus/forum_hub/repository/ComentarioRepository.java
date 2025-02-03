package ludovico.matheus.forum_hub.repository;

import ludovico.matheus.forum_hub.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByTopicoId(Long topicoId);
}
