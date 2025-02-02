package ludovico.matheus.forum_hub.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TopicoUpdateRequest {
    @NotBlank(message = "O título não pode ser vazio")
    private String titulo;

    @NotBlank(message = "A mensagem não pode ser vazia")
    private String mensagem;

    @NotBlank(message = "O curso não pode ser vazio")
    private String curso;
}
