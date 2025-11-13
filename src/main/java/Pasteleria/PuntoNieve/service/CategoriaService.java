package Pasteleria.PuntoNieve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Pasteleria.PuntoNieve.model.Categoria;
import Pasteleria.PuntoNieve.repository.CategoriaRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }
    
}
