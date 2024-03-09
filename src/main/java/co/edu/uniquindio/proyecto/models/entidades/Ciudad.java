package co.edu.uniquindio.proyecto.models.entidades;

import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Ciudad implements Serializable {

    @Id
    @EqualsAndHashCode.Include

    private String codigo;
    private String nombre;




}