package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ActualizacionCuentaDTO(
        @NotBlank String codigo,
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank @Length(max = 100) String nickName,
        @NotBlank String foto,
        @NotBlank @Email @Length(max = 100)  String email,
        @NotBlank @Length(max = 50) String ciudadResidencia
) {
}

