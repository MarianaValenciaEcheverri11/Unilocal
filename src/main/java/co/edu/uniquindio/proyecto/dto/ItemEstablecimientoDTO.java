package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.models.entidades.Horario;
import co.edu.uniquindio.proyecto.models.entidades.Ubicacion;
import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public record ItemEstablecimientoDTO (
        @NotBlank ArrayList<String>imagenes,
        @NotBlank String descripcion,
        @NotBlank String nombre,
        @NotBlank ArrayList<String> telefonos,
        @NotBlank Ubicacion ubicacion,
        @NotBlank ArrayList<Horario> horarios,
        @NotBlank ObjectId codigoUsuario,
        @NotBlank float promedio,
        @NotBlank String categoria
) {



}
