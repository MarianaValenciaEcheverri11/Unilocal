package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDateTime;

public record ComentarioDTO(
        String codigoPublicacion,
        LocalDateTime fecha,
        int valoracion,
        String codigoCliente,
        String resenia
) {
}
