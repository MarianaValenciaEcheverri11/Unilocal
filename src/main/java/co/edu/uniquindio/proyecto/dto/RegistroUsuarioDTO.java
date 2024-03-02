package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistroUsuarioDTO (
    @NotBlank(message = "nombre obligatorio") String nombre,
    @NotBlank String foto,
    @NotBlank @Email String email,
    @NotBlank String cedula,
    @NotBlank String nickname,
    @NotBlank String contrasena,
    @NotBlank String ciudadResidencia
) {
}

