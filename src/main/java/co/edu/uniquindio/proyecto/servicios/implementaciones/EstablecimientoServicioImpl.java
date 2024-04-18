package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.EstablecimientoDTO;
import co.edu.uniquindio.proyecto.models.documentos.Establecimiento;
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

        Establecimiento establecimiento = Establecimiento.builder()
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
    public Establecimiento obtenerEstablecimiento(String codigoEstablecimiento) throws Exception {

        Optional<Establecimiento> establecimiento = establecimientoRepo.findByCodigo(codigoEstablecimiento);

        if (establecimiento.isEmpty()) {
            throw new Exception("El establecimiento no existe");
        }


        return establecimiento.get();

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
    public ArrayList<Establecimiento> listarEstablecimientos() {

        ArrayList<Establecimiento> establecimientos = (ArrayList<Establecimiento>) establecimientoRepo.findAll();

        return establecimientos;
    }

    @Override
    public ArrayList<Establecimiento> listarEstablecimientosPorCategoria(String categoria) {

            ArrayList<Establecimiento> establecimientos = (ArrayList<Establecimiento>) establecimientoRepo.findAll();

            ArrayList<Establecimiento> establecimientosCategoria = new ArrayList<>();

            for (Establecimiento establecimiento : establecimientos) {
                if (establecimiento.getCategoria().equals(categoria)) {
                    establecimientosCategoria.add(establecimiento);
                }
            }

            return establecimientosCategoria;
    }

    @Override
    public String actualizarEstablecimiento(String codigo, EstablecimientoDTO establecimientoDTO) throws Exception {

            if (establecimientoDTO == null) {
                throw new Exception("El establecimiento no puede ser nulo");
            }

            Optional<Establecimiento> establecimiento = establecimientoRepo.findByCodigo(codigo);

            if (establecimiento.isEmpty()) {
                throw new Exception("El establecimiento no existe");
            }

            establecimiento.get().setImagenes(establecimientoDTO.imagenes());
            establecimiento.get().setDescripcion(establecimientoDTO.descripcion());
            establecimiento.get().setNombre(establecimientoDTO.nombre());
            establecimiento.get().setTelefonos(establecimientoDTO.telefonos());
            establecimiento.get().setUbicacion(establecimientoDTO.ubicacion());
            establecimiento.get().setHorarios(establecimientoDTO.horarios());
            establecimiento.get().setCodigoUsuario(establecimientoDTO.codigoUsuario());
            establecimiento.get().setCategoria(establecimientoDTO.categoria());

            establecimientoRepo.save(establecimiento.get());

            return establecimiento.get().getCodigo();
    }

}
