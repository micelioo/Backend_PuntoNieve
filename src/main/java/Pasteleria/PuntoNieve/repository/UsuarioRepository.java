package Pasteleria.PuntoNieve.repository;

import Pasteleria.PuntoNieve.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
