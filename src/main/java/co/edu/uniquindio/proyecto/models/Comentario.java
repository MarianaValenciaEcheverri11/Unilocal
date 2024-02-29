package co.edu.uniquindio.proyecto.models;

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

}
