package com.livraria.crudlivros.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivro;

    @NotBlank(message = "O nome do livro é obrigatório")
    private Double name;

    @ManyToOne
    @JoinColumn(name = "id_autor", nullable = false)
    @NotNull(message = "O autor é obrigatório")
    private Escritor autor;

    @NotNull(message ="O preço é obrigatório")
    private Double preco;

    @NotBlank(message = "Editora é obrigatório")
    private String editora;
    private int anoPublicacao;
}
