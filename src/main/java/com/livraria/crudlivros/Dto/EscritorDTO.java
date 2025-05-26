package com.livraria.crudlivros.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class EscritorDTO {
    private Long idAutor;
    private String name;
    private String email;
    private int idade;

}
