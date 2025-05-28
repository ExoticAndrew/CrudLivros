package com.livraria.crudlivros.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LivroResponseDTO {

    private Long idLivro;
    private String name;
    private String editora;
    private int anoPublicacao;
    private Double preco;

}
