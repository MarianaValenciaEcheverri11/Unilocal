package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record ComentarioDTO(
        @NotBlank(message = "Código obligatorio") String codigoPublicacion,
        @NotBlank(message = "Fecha obligatoria")  @Length(max = 20) String fecha,
        int valoracion,
        @NotBlank(message = "Código del cliente obligatorio") String codigoCliente,
        @NotBlank(message = "Código del establecimiento obligatorio") String codigoEstablecimiento,
        @NotBlank(message = "Reseña obligatoria") @Length(max = 200) String resenia,
        @Length(max = 200) String respuesta
) {
}
