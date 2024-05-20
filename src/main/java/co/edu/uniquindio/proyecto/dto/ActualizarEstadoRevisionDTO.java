package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.models.enums.EstadoPublicacion;
import jakarta.validation.constraints.NotBlank;

public record ActualizarEstadoRevisionDTO (
        String codigoEstablecimiento,
        EstadoPublicacion estado,
        String descripcion
) {
}
