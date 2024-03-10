package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record InicioSesionDTO(
        @NotBlank(message = "Email obligatorio") @Email String email,
        @NotBlank(message = "La contraseña es obligatoria") String contrasena) {
}
