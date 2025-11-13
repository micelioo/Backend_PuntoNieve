package Pasteleria.PuntoNieve.repository;

import Pasteleria.PuntoNieve.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long>{
    
}   

