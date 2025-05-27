package com.livraria.crudlivros.Service;

import com.livraria.crudlivros.Dto.EscritorDTO;
import com.livraria.crudlivros.Dto.LivroRequestDTO;
import com.livraria.crudlivros.Dto.LivroResponseDTO;
import com.livraria.crudlivros.Model.Escritor;
import com.livraria.crudlivros.Model.Livro;
import com.livraria.crudlivros.Repository.EscritorRepository;
import com.livraria.crudlivros.Repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final EscritorRepository escritorRepository;

    public LivroService(LivroRepository livroRepository, EscritorRepository escritorRepository) {
        this.livroRepository = livroRepository;
        this.escritorRepository = escritorRepository;
    }

    public LivroResponseDTO salvar(LivroRequestDTO dto) {
        Escritor autor = escritorRepository.findById(dto.getIdAutor())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        Livro livro = new Livro();
        livro.setName(dto.getName());
        livro.setAutor(autor);
        livro.setPreco(dto.getPreco());
        livro.setEditora(dto.getEditora());
        livro.setAnoPublicacao(dto.getAnoPublicacao());

        livro = livroRepository.save(livro);

        return mapToResponseDTO(livro);
    }

    public List<LivroResponseDTO> listarTodos() {
        return livroRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public LivroResponseDTO buscarPorId(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        return mapToResponseDTO(livro);
    }

    public List<LivroResponseDTO> buscarPorAutor(String nomeAutor) {
        return livroRepository.findByAutorNameContainingIgnoreCase(nomeAutor)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        Escritor autor = escritorRepository.findById(dto.getIdAutor())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        livro.setName(dto.getName());
        livro.setAutor(autor);
        livro.setPreco(dto.getPreco());
        livro.setEditora(dto.getEditora());
        livro.setAnoPublicacao(dto.getAnoPublicacao());

        livro = livroRepository.save(livro);
        return mapToResponseDTO(livro);
    }

    public void deletar(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        livroRepository.delete(livro);
    }

    private LivroResponseDTO mapToResponseDTO(Livro livro) {
        EscritorDTO autorDTO = new EscritorDTO(
                livro.getAutor().getIdAutor(),
                livro.getAutor().getName(),
                livro.getAutor().getEmail(),
                livro.getAutor().getIdade()
        );

        return new LivroResponseDTO(
                livro.getIdLivro(),
                livro.getName(),
                livro.getEditora(),
                livro.getAnoPublicacao(),
                livro.getPreco(),
                autorDTO
        );
    }
}
