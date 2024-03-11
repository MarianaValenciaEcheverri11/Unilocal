package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.models.entidades.Horario;
import co.edu.uniquindio.proyecto.models.entidades.Ubicacion;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;

public record EstablecimientoDTO(
        @NotBlank(message = "codigo obligatorio") String codigo,
        ArrayList<String>imagenes,
        @NotBlank(message = "descripcion obligatorio") @Length(max = 20) String descripcion,
        @NotBlank(message = "nombre obligatorio") @Length(max = 20) String nombre,
        @NotBlank(message = "telefonos obligatorio") @Length(max = 20) ArrayList<String> telefonos,
        @NotBlank(message = "ubicacion obligatorio") @Length(max = 20) Ubicacion ubicacion,
        @NotBlank(message = "horarios obligatorio") @Length(max = 20) ArrayList<Horario> horarios,
        @NotBlank(message = "codigo usuarios obligatorio")@Length(max = 20) String codigoUsuario
) {
}
