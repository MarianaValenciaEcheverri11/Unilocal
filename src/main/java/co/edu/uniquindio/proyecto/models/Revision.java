package co.edu.uniquindio.proyecto.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document("revisiones")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Revision implements Serializable {

    @Id
    @EqualsAndHashCode.Include

    private String codigo;
    private String codigoPublicacion;

    private String descripcion;
    private EstadoPublicacion estado;
    private LocalDateTime fecha;
    private String codigoModerador;
}
