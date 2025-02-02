package ludovico.matheus.forum_hub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos", uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo", "mensagem"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "A mensagem é obrigatória")
    private String mensagem;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    // Valor padrão para o estado; pode ser "NAO_RESPONDIDO" ou outro
    private String estado = "NAO_RESPONDIDO";

    @NotBlank(message = "O autor é obrigatório")
    private String autor;

    @NotBlank(message = "O curso é obrigatório")
    private String curso;
}

