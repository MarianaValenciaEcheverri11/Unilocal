package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.models.enums.EstadoPublicacion;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record RevisionDTO(
        @NotBlank (message = "codigo obligatorio") @Length (max = 20) String codigo,
        @NotBlank (message = "codigo publicacion obligatorio")@Length (max = 20) String codigoPublicacion,
        @NotBlank (message = "descripcion obligatorio") @Length (max = 20) String descripcion,
        @NotBlank (message = "estado publicacion obligatorio")@Length (max = 20) EstadoPublicacion estado,
        @NotBlank (message = "feccha obligatorio") @Length (max = 20) LocalDateTime fecha,
        @NotBlank (message = "codigo moderador obligatorio")@Length (max = 20) String codigoModerador
) {
}
