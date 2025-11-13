package Pasteleria.PuntoNieve.model;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cliente {
    
    @Id
    @Column(name = "run",nullable = false,length = 8,unique = true)
    private String run;

    @Column(nullable = false,length = 1)
    private String dv;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String ape1;

    @Column(nullable = false)
    private String ape2;

    @Column(nullable = false)
    private Date fecnac;

    @Column
    private String correo;  

}
