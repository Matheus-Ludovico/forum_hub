package ludovico.matheus.forum_hub.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A mensagem do comentário é obrigatória")
    private String mensagem;

    @NotBlank(message = "O autor do comentário é obrigatório")
    private String autor;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    // Relação com o tópico - lado de volta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id", nullable = false)
    @JsonBackReference
    private Topico topico;
}
