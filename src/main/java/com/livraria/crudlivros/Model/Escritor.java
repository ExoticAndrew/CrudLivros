package com.livraria.crudlivros.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Escritor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long idAutor;

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @Size(min = 11, max = 11, message = "O cpf deve ter 11 digitos")
    private String cpf;

    @Email(message = "Email inválido" )
    private String email;

    @Min(value = 18, message = "A idade mínima é 18 anos")
    private int idade;



}
