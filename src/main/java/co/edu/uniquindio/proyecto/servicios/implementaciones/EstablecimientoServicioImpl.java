package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.EstablecimientoDTO;
import co.edu.uniquindio.proyecto.models.documentos.Establecimiento;
import co.edu.uniquindio.proyecto.models.entidades.Horario;
import co.edu.uniquindio.proyecto.repository.EstablecimientoRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.EstablecimientoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EstablecimientoServicioImpl implements EstablecimientoServicio {

    private final EstablecimientoRepo establecimientoRepo;

    @Override
    public String crearEstablecimiento(EstablecimientoDTO establecimientoDTO) throws Exception {

        if (establecimientoDTO == null) {
            throw new Exception("El establecimiento no puede ser nulo");
        }

        if (establecimientoRepo.findByCodigo(establecimientoDTO.codigo()).isPresent()) {
            throw new Exception("El establecimiento ya existe");
        }

        Establecimiento establecimiento = Establecimiento.builder()
                .codigo(establecimientoDTO.codigo())
                .imagenes(establecimientoDTO.imagenes())
                .descripcion(establecimientoDTO.descripcion())
                .nombre(establecimientoDTO.nombre())
                .telefonos(establecimientoDTO.telefonos())
                .ubicacion(establecimientoDTO.ubicacion())
                .horarios(establecimientoDTO.horarios())
                .codigoUsuario(establecimientoDTO.codigoUsuario())
                .categoria(establecimientoDTO.categoria())
                .build();

        establecimientoRepo.save(establecimiento);

        return establecimiento.getCodigo();
    }

    @Override
    public EstablecimientoDTO obtenerEstablecimiento(String codigoEstablecimiento) throws Exception {

        Optional<Establecimiento> establecimiento = establecimientoRepo.findByCodigo(codigoEstablecimiento);

        return establecimiento.map(value -> new EstablecimientoDTO(
                value.getCodigo(),
                value.getImagenes(),
                value.getDescripcion(),
                value.getNombre(),
                value.getTelefonos(),
                value.getUbicacion(),
                value.getHorarios(),
                value.getCodigoUsuario(),
                value.getCategoria()
        )).orElse(null);

    }

    @Override
    public String eliminarEstablecimiento(String codigoEstablecimiento) throws Exception {

        Optional<Establecimiento> establecimiento = establecimientoRepo.deleteByCodigo(codigoEstablecimiento);

        System.err.println(establecimiento);

        if (establecimiento.isEmpty()) {
            throw new Exception("El establecimiento no existe");
        }

        return "Establecimiento con id: " + establecimiento.get().getCodigo() + " eliminado";
    }

    @Override
    public ArrayList<EstablecimientoDTO> listarEstablecimientos() {
        return null;
    }

    @Override
    public ArrayList<EstablecimientoDTO> listarEstablecimientosPorCategoria(String categoria) {
        return null;
    }

    @Override
    public String actualizarEstablecimiento(String codigoEstablecimient) throws Exception {
        return null;
    }

}
