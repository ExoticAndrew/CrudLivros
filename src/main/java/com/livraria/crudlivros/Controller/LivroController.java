package com.livraria.crudlivros.Controller;

import com.livraria.crudlivros.Dto.LivroRequestDTO;
import com.livraria.crudlivros.Dto.LivroResponseDTO;
import com.livraria.crudlivros.Service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private final LivroService livroService;

    public LivroController(LivroService livroService){
        this.livroService = livroService;
    }
    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> listar(){
        return ResponseEntity.ok(livroService.listarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> buscarPorId(@PathVariable long id){
        return ResponseEntity.ok(livroService.buscarPorId(id));
    }
    @GetMapping("/autor")
    public ResponseEntity<List<LivroResponseDTO>> buscarPorAutor(@RequestParam String name){
        return ResponseEntity.ok(livroService.buscarPorAutor(name));
    }
    @PostMapping
    public ResponseEntity<LivroResponseDTO> salvar(@Valid @RequestBody LivroRequestDTO dto){
        return ResponseEntity.ok(livroService.salvar(dto));
    }
@PutMapping("/{id}")
public ResponseEntity<LivroResponseDTO> atualizar(@PathVariable long id, @Valid @RequestBody LivroRequestDTO dto){
    return ResponseEntity.ok(livroService.atualizar(id,dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
