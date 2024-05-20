package co.edu.uniquindio.proyecto.models.documentos;

import co.edu.uniquindio.proyecto.models.entidades.Horario;
import co.edu.uniquindio.proyecto.models.entidades.Ubicacion;
import lombok.*;
import org.bson.types.ObjectId;
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
    private ObjectId codigoUsuario;
    private float promedio;
    private String categoria;

    @Builder
    public Establecimiento(String codigo, ArrayList<String> imagenes, String descripcion, String nombre, ArrayList<String> telefonos, Ubicacion ubicacion, ArrayList<Horario> horarios, ObjectId codigoUsuario, float promedio, String categoria) {
        this.codigo = codigo;
        this.imagenes = imagenes;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.telefonos = telefonos;
        this.ubicacion = ubicacion;
        this.horarios = horarios;
        this.codigoUsuario = codigoUsuario;
        this.promedio = promedio;
        this.categoria = categoria;
    }
}
