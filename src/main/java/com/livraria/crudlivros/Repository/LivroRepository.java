package com.livraria.crudlivros.Repository;

import com.livraria.crudlivros.Model.Escritor;
import com.livraria.crudlivros.Model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findAllByAutor(Escritor autor);
    List<Livro> findByAutorNameContainingIgnoreCase(String nameAutor);
}
