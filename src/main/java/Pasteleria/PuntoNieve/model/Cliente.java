package Pasteleria.PuntoNieve.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @Column(name = "run", nullable = false, length = 8, unique = true)
    private String run;

    @Column(nullable = false, length = 1)
    private String dv;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 50)
    private String ape1;

    @Column(nullable = false, length = 50)
    private String ape2;

    @Column(nullable = false)
    private LocalDate fecnac;

    @Column(length = 100, unique = true)
    private String correo;
}