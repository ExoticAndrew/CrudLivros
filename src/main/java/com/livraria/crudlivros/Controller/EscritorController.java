package com.livraria.crudlivros.Controller;

import com.livraria.crudlivros.Model.Escritor;
import com.livraria.crudlivros.Service.EscritorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/escritores")

public class EscritorController {
    private final EscritorService escritorService;

    public EscritorController(EscritorService escritorService){
        this.escritorService = escritorService;
    }
@GetMapping
    public ResponseEntity<List<Escritor>> listar(){
        return ResponseEntity.ok(escritorService.listarTodos());
}
@GetMapping("/{id}")
    public ResponseEntity<Escritor>buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(escritorService.buscarPorId(id));
}
@PostMapping
    public ResponseEntity<Escritor>salvar(@Valid @RequestBody Escritor escritor){
        return ResponseEntity.ok(escritorService.salvar(escritor));
}
@PutMapping("/{id}")
    public ResponseEntity<Escritor> atualizar(@PathVariable Long id, @Valid @RequestBody Escritor escritor){
        return ResponseEntity.ok(escritorService.atualizar(id,escritor));

    }
    @DeleteMapping("/{id}")

            public ResponseEntity<Void> deletar(@PathVariable long id){
            escritorService.deletar(id);
            return ResponseEntity.noContent().build();
    }
}

