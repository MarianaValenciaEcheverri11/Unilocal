package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RegistroClienteDTO(
    @NotBlank(message = "Nombre obligatorio") @Length(max = 20) String nombre,
    String foto,
    @NotBlank(message = "Email obligatorio") @Email String email,
    @NotBlank(message = "Nickname obligatorio") @Length(max = 20) String nickname,
    @NotBlank(message = "Contraseña obligatoria") @Length(max = 10) String contrasena,
    String ciudadResidencia
) {
}