package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.RevisionDTO;
import co.edu.uniquindio.proyecto.models.documentos.Revision;
import co.edu.uniquindio.proyecto.models.enums.EstadoPublicacion;

import java.util.Optional;

public interface RevisionServicio {

    String enviarRevision(RevisionDTO revisionDTO) throws Exception;
    Optional<Revision> consultarRevisiones(String codigo) throws Exception;
    String eliminarRevision(String codigo) throws Exception;
}
