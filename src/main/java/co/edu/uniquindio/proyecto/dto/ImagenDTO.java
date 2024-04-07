package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ImagenDTO(
        @NotBlank(message = "Código obligatorio") String codigo,
        @NotBlank(message = "Imagen obligatoria")  String imagen
) {
}