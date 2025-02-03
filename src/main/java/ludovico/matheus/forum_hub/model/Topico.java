package ludovico.matheus.forum_hub.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

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

    private String estado = "NAO_RESPONDIDO";

    @NotBlank(message = "O autor é obrigatório")
    private String autor;

    @NotBlank(message = "O curso é obrigatório")
    private String curso;

    // Relação com os comentários - lado gerenciado
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Comentario> comentarios;
}

