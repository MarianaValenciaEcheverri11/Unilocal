package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ActualizacionCuentaDTO(
        @NotBlank String id,
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank String foto,
        @NotBlank @Email String email,
        @NotBlank String ciudadResidencia
) {
}

