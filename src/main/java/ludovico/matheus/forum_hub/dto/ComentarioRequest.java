package ludovico.matheus.forum_hub.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ComentarioRequest {

    @NotBlank(message = "A mensagem do comentário é obrigatória")
    private String mensagem;

    @NotBlank(message = "O autor do comentário é obrigatório")
    private String autor;
}
