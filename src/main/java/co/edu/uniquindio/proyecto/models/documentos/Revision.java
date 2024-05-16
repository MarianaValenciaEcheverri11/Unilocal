package co.edu.uniquindio.proyecto.models.documentos;

import co.edu.uniquindio.proyecto.models.enums.EstadoPublicacion;
import lombok.*;
import org.bson.types.ObjectId;
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
    private ObjectId codigoEstablecimiento;
    private String descripcion;
    private EstadoPublicacion estado;
    private String fecha;
    private String codigoModerador;

    @Builder
    public Revision(String codigo, ObjectId codigoEstablecimiento, String descripcion, EstadoPublicacion estado, String fecha, String codigoModerador) {
        this.codigo = codigo;
        this.codigoEstablecimiento = codigoEstablecimiento;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha = fecha;
        this.codigoModerador = codigoModerador;
    }
}
