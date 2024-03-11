package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.RevisionDTO;
import co.edu.uniquindio.proyecto.models.documentos.Revision;
import co.edu.uniquindio.proyecto.servicios.intefaces.RevisionServicio;

import java.util.Optional;

public class RevisionServicioImpl implements RevisionServicio {

    @Override
    public void establecerRevision() throws Exception {

    }

    @Override
    public String enviarRevision(RevisionDTO revisionDTO) throws Exception {

        if (revisionDTO == null) {
            throw new Exception("La revision no puede ser nula");
        }
        if (revisionDTO.codigo().isEmpty()) {
            throw new Exception("El codigo de la revision es obligatorio");
        }

        Revision revision = Revision.builder().
                codigo(revisionDTO.codigo()).
                codigoPublicacion(revisionDTO.codigoPublicacion()).
                descripcion(revisionDTO.descripcion()).
                estado(revisionDTO.estado()).
                fecha(revisionDTO.fecha()).
                codigoModerador(revisionDTO.codigoModerador()).
                build(
        );

        return revision.getCodigo();

    }

    @Override
    public Optional<Revision> consultarRevisiones(String codigo) throws Exception {

        if (codigo.isEmpty()) {
            throw new Exception("El codigo de la revision es obligatorio");
        }

        return Optional.empty();
    }

    @Override
    public String mensajeRevision(String mensaje) throws Exception {

            if (mensaje.isEmpty()) {
                throw new Exception("El mensaje de la revision es obligatorio");
            }

            return mensaje;
    }

    @Override
    public String aprobarRevision() throws Exception {

            return null;

    }

    @Override
    public String rechazarRevision() throws Exception {

                return null;
    }
}
