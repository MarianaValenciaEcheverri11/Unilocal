package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.ResponderComentarioDTO;
import co.edu.uniquindio.proyecto.models.documentos.Comentario;

import java.util.ArrayList;
import java.util.Optional;

public interface ComentarioServicio {
    String crearComentario(ComentarioDTO comentarioDTO) throws Exception;
    String responderComentario(String codigo, ResponderComentarioDTO responderComentarioDTO) throws Exception;
    Optional<ArrayList<Comentario>> listarComentariosPorEstablecimiento(String idEstablecimiento) throws Exception;
    String eliminarComentario(String idComentario) throws Exception;
    String actualizarComentario(String codigo, ComentarioDTO comentarioDTO) throws Exception;
}