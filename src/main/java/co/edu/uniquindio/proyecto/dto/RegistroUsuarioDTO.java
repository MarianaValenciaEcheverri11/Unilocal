package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistroUsuarioDTO (
    @NotBlank(message = "Nombre obligatorio") String nombre,
    @NotBlank(message = "Foto obligatoria") String foto,
    @NotBlank(message = "Email obligatorio") @Email String email,
    @NotBlank(message = "Cédula obligatoria") String cedula,
    @NotBlank(message = "Nickname obligatorio") String nickname,
    @NotBlank(message = "Contraseña obligatoria") String contrasena,
    @NotBlank(message = "Ciudad residencia obligatoria") String ciudadResidencia
) {
}