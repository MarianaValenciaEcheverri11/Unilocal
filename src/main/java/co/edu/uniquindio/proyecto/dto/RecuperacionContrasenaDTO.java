package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RecuperacionContrasenaDTO(
        @NotBlank(message = "Código obligatorio") String codigoSeguridad,
        @NotBlank @Length(max = 100) String codigoCliente,
        @NotBlank(message = "token obligatoria") @Length(max = 100) String token,
        @NotBlank(message = "Contraseña obligatoria") @Length(min = 7) String contrasenaNueva
) {
}
