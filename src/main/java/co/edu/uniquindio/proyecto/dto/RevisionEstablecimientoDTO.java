package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.models.entidades.Horario;
import co.edu.uniquindio.proyecto.models.entidades.Ubicacion;
import co.edu.uniquindio.proyecto.models.enums.EstadoPublicacion;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;

public record RevisionEstablecimientoDTO(
        ArrayList<String> imagenes,
        @NotBlank(message = "descripcion obligatorio") @Length(max = 5000) String descripcion,
        @NotBlank(message = "nombre obligatorio") @Length(max = 20) String nombre,
        @NotBlank(message = "codigo usuarios obligatorio") @Length(max = 40) String codigoUsuario,
        @NotBlank(message = "categoria obligatorio") @Length(max = 20) String estado,
        @Length(max = 20) String _id
) {
}
