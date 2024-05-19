package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RecuperacionContrasenaDTO(
        @NotBlank @Length(max = 100) String email,
        @NotBlank(message = "Contraseña obligatoria") @Length(min = 7) String contrasenaNueva
) {
}
