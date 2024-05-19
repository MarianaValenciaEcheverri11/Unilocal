package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.models.enums.EstadoPublicacion;
import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record RevisionDTO(
        @NotBlank (message = "codigo publicacion obligatorio") String codigoEstablecimiento,
        @NotBlank (message = "descripcion obligatorio")  String descripcion,
        EstadoPublicacion estado,
        @NotBlank (message = "feccha obligatorio") String fecha,
        @NotBlank (message = "codigo moderador obligatorio") String codigoModerador
) {
}
