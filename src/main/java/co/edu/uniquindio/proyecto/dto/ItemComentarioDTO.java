package co.edu.uniquindio.proyecto.dto;

public record ItemComentarioDTO(
        String codigoComentario,
        String codigoUsuario,
        String nombreUsuario,
        String fotoUsuario,
        String resenia,
        String respuesta,
        int valoracion
) {
}