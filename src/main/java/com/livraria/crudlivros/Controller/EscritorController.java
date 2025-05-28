package com.livraria.crudlivros.Controller;

import com.livraria.crudlivros.Dto.EscritorDTO;
import com.livraria.crudlivros.Model.Escritor;
import com.livraria.crudlivros.Service.EscritorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/escritores")

public class EscritorController {
    private final EscritorService escritorService;

    public EscritorController(EscritorService escritorService) {
        this.escritorService = escritorService;
    }

    @GetMapping
    public ResponseEntity<List<Escritor>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(escritorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Escritor> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(escritorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Escritor> salvar(@Valid @RequestBody EscritorDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(escritorService.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Escritor> atualizar(@PathVariable Long id, @Valid @RequestBody EscritorDTO escritor) {
        return ResponseEntity.ok(escritorService.atualizar(id, escritor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        escritorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

