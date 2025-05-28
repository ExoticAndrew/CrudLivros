package com.livraria.crudlivros.Repository;

import com.livraria.crudlivros.Model.Escritor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface EscritorRepository extends JpaRepository<Escritor, Long> {

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

    List<Escritor> findByNameContainingIgnoreCase(String nome);
}
