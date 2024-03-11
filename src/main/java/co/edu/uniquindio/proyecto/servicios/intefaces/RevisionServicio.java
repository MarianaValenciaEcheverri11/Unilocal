package co.edu.uniquindio.proyecto.servicios.intefaces;

import co.edu.uniquindio.proyecto.dto.RevisionDTO;
import co.edu.uniquindio.proyecto.models.documentos.Revision;

import java.util.Optional;

public interface RevisionServicio {

    void establecerRevision() throws Exception;
    String enviarRevision(RevisionDTO revisionDTO) throws Exception;
    Optional<Revision> consultarRevisiones(String codigo) throws Exception;
    String mensajeRevision(String mensaje) throws Exception;
    String aprobarRevision() throws Exception;
    String rechazarRevision() throws Exception;

}
