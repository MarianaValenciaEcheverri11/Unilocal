package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.RevisionDTO;
import co.edu.uniquindio.proyecto.models.documentos.Revision;
import co.edu.uniquindio.proyecto.models.enums.EstadoPublicacion;
import co.edu.uniquindio.proyecto.repository.RevisionRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.RevisionServicio;

import java.util.Optional;

public class RevisionServicioImpl implements RevisionServicio {

    RevisionRepo revisionRepo;

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
        revisionRepo.save(revision);
        return revision.getCodigo();

    }

    @Override
    public Optional<Revision> consultarRevisiones(String codigo) throws Exception {
        if (codigo.isEmpty()) {
            throw new Exception("El codigo de la revision es obligatorio");
        }
        return revisionRepo.findById(codigo);
    }

    @Override
    public String eliminarRevision(String codigo) throws Exception {
        if (codigo.isEmpty()) {
            throw new Exception("El codigo de la revision es obligatorio");
        }
        revisionRepo.deleteById(codigo);
        return "Revision eliminada";
    }


}
