package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ResponderComentarioDTO(
    @NotBlank(message = "Id obligatorio") @Length(max = 20) String idPublicacion,
    @NotBlank(message = "Comentario obligatorio") @Length(max = 100) String ComentarioInsertado

){
}
