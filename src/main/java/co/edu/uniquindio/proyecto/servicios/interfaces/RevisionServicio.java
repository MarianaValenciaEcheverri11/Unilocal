package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ActualizarEstadoRevisionDTO;
import co.edu.uniquindio.proyecto.dto.ItemRevisionDTO;
import co.edu.uniquindio.proyecto.dto.RevisionDTO;
import co.edu.uniquindio.proyecto.models.documentos.Revision;
import co.edu.uniquindio.proyecto.models.enums.EstadoPublicacion;

import java.util.ArrayList;
import java.util.Optional;

public interface RevisionServicio {

    String enviarRevision(RevisionDTO revisionDTO) throws Exception;
    ItemRevisionDTO consultarRevisiones(String codigo) throws Exception;
    ArrayList<Revision> obtenerTodasRevisiones() throws Exception;
    ItemRevisionDTO obtenerRevisionPorCodigoEstablecimiento(String codigoEstablecimiento)  throws Exception;

    RevisionDTO cambiarEstadoRevision(String codigo, ActualizarEstadoRevisionDTO actualizarEstadoRevisionDTO) throws Exception;
}
