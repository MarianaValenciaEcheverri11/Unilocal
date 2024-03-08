package co.edu.uniquindio.proyecto.servicios.intefaces;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;

public interface ComentarioServicio {

    void crearComentario(ComentarioDTO comentarioDTO);
    void responderComentario(ComentarioDTO comentarioDTO);
    void listarComentariosPorPublicacion(String RespuestaComentarioDTO);

}