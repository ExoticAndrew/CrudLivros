package com.livraria.crudlivros.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LivroRequestDTO {
    @NotBlank(message = "O nome do livro é obrigatório")
    private String name;

    @NotNull(message = "O id do autor é obrigatório")
    private Long idAutor;

    @Min(value = 5, message = "O valor minimo de um livro é 5 reais")
    @NotNull(message = "O preço é obrigatório")
    private Double preco;

    @NotBlank(message = "A editora é obrigatótia")
    private String editora;

    @Min(value = 1850, message = "O ano de publicação deve ser maior ou igual a 1850")
    @NotNull(message = "O ano de publicação é obrigatório")
    private int anoPublicacao;
}
