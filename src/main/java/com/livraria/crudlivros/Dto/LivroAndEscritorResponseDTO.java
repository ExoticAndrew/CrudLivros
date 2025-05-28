package com.livraria.crudlivros.Dto;

import java.util.List;

public record LivroAndEscritorResponseDTO(EscritorDTO escritor, List<LivroResponseDTO> livros) {

}
