package com.livraria.crudlivros.Service;

import com.livraria.crudlivros.Dto.EscritorDTO;
import com.livraria.crudlivros.Exception.EmailJaCadastradoException;
import com.livraria.crudlivros.Model.Escritor;
import com.livraria.crudlivros.Repository.EscritorRepository;
import com.livraria.crudlivros.Exception.CpfJaCadastradoException;
import com.livraria.crudlivros.Exception.EscritorNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EscritorService {

    private final EscritorRepository escritorRepository;

    public EscritorService(EscritorRepository escritorRepository) {
        this.escritorRepository = escritorRepository;
    }

    public List<Escritor> listarTodos() {
        return escritorRepository.findAll();
    }

    public Escritor buscarPorId(Long id) {
        return escritorRepository.findById(id)
                .orElseThrow(() -> new EscritorNotFoundException("Escritor não encontrado"));
    }

    public List<Escritor> buscarPorNome(String nome) {
        return escritorRepository.findByNameContainingIgnoreCase(nome);
    }

    public EscritorDTO salvar(EscritorDTO dto) {
        if (escritorRepository.existsByCpf(dto.getCpf())) {
            throw new CpfJaCadastradoException("Já existe um escritor com esse CPF.");
        }

        if (escritorRepository.existsByEmail(dto.getEmail())) {
            throw new EmailJaCadastradoException("Já existe um escritor com esse e-mail.");
        }

        if (dto.getIdade() < 18) {
            throw new IllegalArgumentException("A idade do escritor deve ser maior ou igual a 18 anos.");
        }

        Escritor escritor = escritorRepository.save(new Escritor(dto));

        return new EscritorDTO(escritor);
    }

    public EscritorDTO atualizar(Long id, EscritorDTO dto) {
        Escritor existente = buscarPorId(id);

        existente.setName(dto.getName());
        existente.setCpf(dto.getCpf());
        existente.setEmail(dto.getEmail());
        existente.setIdade(dto.getIdade());

        Escritor escritor = escritorRepository.save(existente);

        return new EscritorDTO(escritor);
    }

    public void deletar(long id) {
        Escritor escritor = buscarPorId(id);
        escritorRepository.delete(escritor);
    }
}
