package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.models.enums.EstadoPublicacion;
import jakarta.validation.constraints.NotBlank;

public record ItemRevisionDTO (
        @NotBlank String codigo,
        @NotBlank(message = "codigo publicacion obligatorio") String codigoEstablecimiento,
        @NotBlank (message = "descripcion obligatorio")  String descripcion,
        EstadoPublicacion estado,
        @NotBlank (message = "feccha obligatorio") String fecha,
        @NotBlank (message = "codigo moderador obligatorio") String codigoModerador
){
}
