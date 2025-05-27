package com.livraria.crudlivros.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivro;

    @NotBlank(message = "O nome do livro é obrigatório")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_autor", nullable = false)
    @NotNull(message = "O autor é obrigatório")
    private Escritor autor;

    @NotNull(message = "O preço é obrigatório")
    private Double preco;

    @NotBlank(message = "A editora é obrigatória")
    private String editora;

    @NotNull(message = "O ano de publicação é obrigatório")
    private Integer anoPublicacao;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;
}
