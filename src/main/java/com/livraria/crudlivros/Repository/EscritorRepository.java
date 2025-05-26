package com.livraria.crudlivros.Repository;

import com.livraria.crudlivros.Model.Escritor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EscritorRepository extends JpaRepository<Escritor, Long> {
}
