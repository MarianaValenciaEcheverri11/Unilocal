package co.edu.uniquindio.proyecto.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;

@Document("establecimientos")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Establecimiento implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private ArrayList<String> imagenes;
    private String descripcion;
    private String nombre;
    private ArrayList<String> telefonos;
    private Ubicacion ubicacion;
    private ArrayList<Horario> horarios;
    private String codigoUsuario;




}
