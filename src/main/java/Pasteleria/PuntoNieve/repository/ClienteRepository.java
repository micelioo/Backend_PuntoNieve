package Pasteleria.PuntoNieve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Pasteleria.PuntoNieve.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,String>{

}
