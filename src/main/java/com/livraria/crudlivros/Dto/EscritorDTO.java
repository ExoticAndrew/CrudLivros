package com.livraria.crudlivros.Dto;

import com.livraria.crudlivros.Model.Escritor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EscritorDTO {

    private Long id;

    @NotBlank(message = "O nome do escritor é obrigatório")
    private String name;

    @CPF @NotNull(message = "O CPF do escritor é obrigatório")
    private String cpf;

    @Email @NotBlank(message = "O email do escritor é obrigatório")
    private String email;

    @NotNull(message = "A idade do escritor é obrigatória")
    private int idade;

    public EscritorDTO(Escritor escritor) {
        this.id = escritor.getIdAutor();
        this.cpf = escritor.getCpf();
        this.email = escritor.getEmail();
        this.idade = escritor.getIdade();
        this.name = escritor.getName();
    }

}
