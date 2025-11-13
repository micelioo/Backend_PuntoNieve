package Pasteleria.PuntoNieve.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nombre_usuario", nullable = false, unique = true, length = 50)
    private String nombreUsuario;

    @Column(nullable = false, length = 20)
    private String rol;

    @OneToOne
    @JoinColumn(name = "run_cliente", referencedColumnName = "run")
    private Cliente cliente;

    @JsonProperty("runCliente")
    public String getRunCliente() {
        return (cliente != null) ? cliente.getRun() : null;
    }

    @JsonProperty("nombreCompletoCliente")
    public String getNombreCompletoCliente() {
        if (cliente == null) return null;
        return cliente.getNombres() + " " + cliente.getApe1() + " " + cliente.getApe2();
    }

    @JsonProperty("correoCliente")
    public String getCorreoCliente() {
        return (cliente != null) ? cliente.getCorreo() : null;
    }
}