package co.edu.uniquindio.proyecto.models.documentos;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    private LocalDateTime fecha;
    private int valoracion;
    private String codigoCliente;
    private String resenia;
    private String respuesta;

    @Builder

    public Comentario(String codigo, LocalDateTime fecha, int valoracion, String codigoCliente, String resenia, String respuesta) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.valoracion = valoracion;
        this.codigoCliente = codigoCliente;
        this.resenia = resenia;
        this.respuesta = respuesta;
    }
}