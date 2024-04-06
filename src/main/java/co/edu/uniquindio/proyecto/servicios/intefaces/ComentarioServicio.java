package co.edu.uniquindio.proyecto.servicios.intefaces;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.models.documentos.Comentario;

import java.util.ArrayList;
import java.util.Optional;

public interface ComentarioServicio {

    String crearComentario(ComentarioDTO comentarioDTO) throws Exception;
    String responderComentario(String idPublicacion, String ComentarioInsertado) throws Exception;
    Optional<ArrayList<Comentario>> listarComentariosPorEstablecimiento(String idEstablecimiento) throws Exception;

}