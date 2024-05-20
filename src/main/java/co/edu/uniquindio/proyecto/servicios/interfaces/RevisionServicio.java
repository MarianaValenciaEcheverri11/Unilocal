package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.RevisionDTO;
import co.edu.uniquindio.proyecto.models.documentos.Revision;
import co.edu.uniquindio.proyecto.models.enums.EstadoPublicacion;

import java.util.ArrayList;
import java.util.Optional;

public interface RevisionServicio {

    String enviarRevision(RevisionDTO revisionDTO) throws Exception;
    RevisionDTO consultarRevisiones(String codigo) throws Exception;
    ArrayList<Revision> obtenerTodasRevisiones() throws Exception;
    Revision obtenerRevisionPorCodigoEstablecimiento(String codigoEstablecimiento)  throws Exception;

    RevisionDTO cambiarEstadoRevision(String codigo, RevisionDTO revisionDTO) throws Exception;
}
