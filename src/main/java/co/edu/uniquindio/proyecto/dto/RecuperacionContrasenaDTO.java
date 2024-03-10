package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RecuperacionContrasenaDTO(
        @NotBlank(message = "Código obligatorio") String codigoSeguridad,
        @NotBlank(message = "Contraseña obligatoria") @Length(max = 10) String contrasenaNueva
) {
}
