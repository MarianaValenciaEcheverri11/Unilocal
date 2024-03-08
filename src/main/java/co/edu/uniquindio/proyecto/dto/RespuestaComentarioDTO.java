package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDateTime;

public record RespuestaComentarioDTO(
        String codigoPublicacion,
        String codigoComentario,
        LocalDateTime fecha,
        int valoracion,
        String codigoCliente,
        String resenia
) {
}