package com.livraria.crudlivros.Service;

import com.livraria.crudlivros.Dto.EscritorDTO;
import com.livraria.crudlivros.Dto.LivroAndEscritorResponseDTO;
import com.livraria.crudlivros.Dto.LivroRequestDTO;
import com.livraria.crudlivros.Dto.LivroResponseDTO;
import com.livraria.crudlivros.Model.Escritor;
import com.livraria.crudlivros.Model.Livro;
import com.livraria.crudlivros.Repository.EscritorRepository;
import com.livraria.crudlivros.Repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final EscritorService escritorService;

    public LivroService(LivroRepository livroRepository, EscritorService escritorService) {
        this.livroRepository = livroRepository;
        this.escritorService = escritorService;
    }

    public LivroResponseDTO salvar(LivroRequestDTO dto) {
        Escritor autor = escritorService.buscarPorId(dto.getIdAutor());

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

    public List<LivroResponseDTO> buscarTodosPorIdAutor(Long id) {
        Escritor autor = escritorService.buscarPorId(id);
        return livroRepository.findAllByAutor(autor)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<LivroAndEscritorResponseDTO> buscarPorAutor(String nomeAutor) {
        List<Escritor> autores = escritorService.buscarPorNome(nomeAutor);
        List<LivroAndEscritorResponseDTO> dtos = new ArrayList<>();

        for (Escritor autor : autores) {
            List<LivroResponseDTO> livrosDto = buscarTodosPorIdAutor(autor.getIdAutor());
            dtos.add(new LivroAndEscritorResponseDTO(new EscritorDTO(autor), livrosDto));
        }

        return dtos;
    }

    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        Escritor autor = escritorService.buscarPorId(dto.getIdAutor());

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
        return new LivroResponseDTO(
                livro.getIdLivro(),
                livro.getName(),
                livro.getEditora(),
                livro.getAnoPublicacao(),
                livro.getPreco()
        );
    }
}
