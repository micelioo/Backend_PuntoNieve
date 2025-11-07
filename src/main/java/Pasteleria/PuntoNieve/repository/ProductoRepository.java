package Pasteleria.PuntoNieve.repository;

import Pasteleria.PuntoNieve.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
