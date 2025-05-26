package com.livraria.crudlivros.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LivroRequestDTO {
    @NotBlank(message = "O nome do livro é obrigatório")
    private String name;

    @NotNull(message = "O id do autor é obrigatório")
    private Long idAutor;

    @NotNull(message = "O preço é obrigatório")
    private Double preco;

    @NotBlank(message = "A editora é obrigatótia")
    private String editora;

    private int anoPublicacao;
}
