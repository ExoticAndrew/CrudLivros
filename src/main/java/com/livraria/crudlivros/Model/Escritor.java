package com.livraria.crudlivros.Model;

import com.livraria.crudlivros.Dto.EscritorDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Escritor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutor;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int idade;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;

    public Escritor(EscritorDTO dto) {
        this.name = dto.getName();
        this.cpf = dto.getCpf();
        this.email = dto.getEmail();
        this.idade = dto.getIdade();
    }
}
