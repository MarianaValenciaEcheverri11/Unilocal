package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.dto.RevisionDTO;
import co.edu.uniquindio.proyecto.models.documentos.Cliente;
import co.edu.uniquindio.proyecto.models.documentos.Establecimiento;
import co.edu.uniquindio.proyecto.models.documentos.Revision;
import co.edu.uniquindio.proyecto.models.enums.EstadoPublicacion;
import co.edu.uniquindio.proyecto.repository.ClienteRepo;
import co.edu.uniquindio.proyecto.repository.EstablecimientoRepo;
import co.edu.uniquindio.proyecto.repository.RevisionRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
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
    private final EmailServicio emailServicio;
    private final ClienteRepo clienteRepo;
    private final EstablecimientoRepo establecimientoRepo;

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

        System.err.println(revision.get());

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
        System.err.println(revisionRepo.findAll());
        return (ArrayList<Revision>) revisionRepo.findAll();
    }

    @Override
    public RevisionDTO cambiarEstadoRevision(String codigo, RevisionDTO revisionDTO) throws Exception {

        if (codigo.isEmpty()) {
            throw new Exception("El codigo de la revision es obligatorio");
        }
        if (revisionDTO == null) {
            throw new Exception("La revision no puede ser nula");
        }
        Optional<Revision> revision = revisionRepo.findByCodigo(codigo);

        if (revision.isEmpty()) {
            throw new Exception("La revision no existe");
        }
        revision.get().setEstado(revisionDTO.estado());
        revisionRepo.save(revision.get());

        Optional<Establecimiento> establecimiento = establecimientoRepo.findByCodigo(revisionDTO.codigoEstablecimiento());
        Optional<Cliente> cliente = clienteRepo.findByCodigo(establecimiento.get().getCodigoUsuario());


        EmailDTO emailDTO = new EmailDTO(
                cliente.get().getEmail(),
                "Cambio de estado de su revision",
                "Se ha cambiado el estado de su revision \n" +
                        "Fecha: " + revision.get().getFecha() + "\n" +
                        "Estado: " + revisionDTO.estado() + "\n" +
                        "Gracias por usar nuestra plataforma."
        );

        emailServicio.enviarEmail(emailDTO);


        return new RevisionDTO(
                revision.get().getCodigoEstablecimiento(),
                revision.get().getDescripcion(),
                revision.get().getEstado(),
                revision.get().getFecha(),
                revision.get().getCodigoModerador()
        );

    }


}
