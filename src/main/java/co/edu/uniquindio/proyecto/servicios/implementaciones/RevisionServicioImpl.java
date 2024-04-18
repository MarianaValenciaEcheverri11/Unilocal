package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.RevisionDTO;
import co.edu.uniquindio.proyecto.models.documentos.Revision;
import co.edu.uniquindio.proyecto.models.enums.EstadoPublicacion;
import co.edu.uniquindio.proyecto.repository.RevisionRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.RevisionServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RevisionServicioImpl implements RevisionServicio {

    private final RevisionRepo revisionRepo;

    @Override
    public String enviarRevision(RevisionDTO revisionDTO) throws Exception {

        if (revisionDTO == null) {
            throw new Exception("La revision no puede ser nula");
        }

        Revision revision = Revision.builder().
                codigoEstablecimiento(revisionDTO.codigoEstablecimiento()).
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
    public RevisionDTO consultarRevisiones(String codigo) throws Exception {
        if (codigo.isEmpty()) {
            throw new Exception("El codigo de la revision es obligatorio");
        }
        Optional<Revision> revision = revisionRepo.findById(codigo);
        if (revision.isEmpty()) {
            throw new Exception("La revision no existe");
        }
        return new RevisionDTO(
                revision.get().getCodigoEstablecimiento(),
                revision.get().getDescripcion(),
                revision.get().getEstado(),
                revision.get().getFecha(),
                revision.get().getCodigoModerador()
        );
    }

    @Override
    public ArrayList<Revision> obtenerTodasRevisiones() throws Exception {
        return (ArrayList<Revision>) revisionRepo.findAll();
    }

}
