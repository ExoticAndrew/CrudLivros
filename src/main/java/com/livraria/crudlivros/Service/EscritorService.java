package com.livraria.crudlivros.Service;

import com.livraria.crudlivros.Model.Escritor;
import com.livraria.crudlivros.Repository.EscritorRepository;
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
                .orElseThrow(() -> new RuntimeException("Escritor não encontrado"));
    }

    public Escritor salvar(Escritor escritor) {
        if (escritorRepository.existsByCpf(escritor.getCpf())) {
            throw new RuntimeException("Já existe um escritor com esse CPF.");
        }
        return escritorRepository.save(escritor);
    }

    public Escritor atualizar(Long id, Escritor escritorAtualizado) {
        Escritor existente = buscarPorId(id);

        existente.setName(escritorAtualizado.getName());
        existente.setCpf(escritorAtualizado.getCpf());
        existente.setEmail(escritorAtualizado.getEmail());
        existente.setIdade(escritorAtualizado.getIdade());

        return escritorRepository.save(existente);
    }

    public void deletar(long id) {
        Escritor escritor = buscarPorId(id);
        escritorRepository.delete(escritor);
    }
}
