package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDTO(
        @NotBlank(message = "email obligatorio") @Email String destinatario,
        @NotBlank(message = "asunto obligatorio") String asunto,
        @NotBlank(message = "cuerpo obligatorio") String cuerpo
        ) {
}
