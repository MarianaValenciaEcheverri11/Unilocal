package co.edu.uniquindio.proyecto.models.documentos;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("comentarios")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Comentario implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String fecha;
    private int valoracion;
    private ObjectId codigoCliente;
    private String codigoEstablecimiento;
    private String resenia;
    private String respuesta;

    @Builder
    public Comentario(String fecha, int valoracion, ObjectId codigoCliente, String codigoEstablecimiento, String resenia, String respuesta) {
        this.fecha = fecha;
        this.valoracion = valoracion;
        this.codigoCliente = codigoCliente;
        this.codigoEstablecimiento = codigoEstablecimiento;
        this.resenia = resenia;
        this.respuesta = respuesta;
    }
}