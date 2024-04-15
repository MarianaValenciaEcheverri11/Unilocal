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
                .build();

        establecimientoRepo.save(establecimiento);

        return establecimiento.getCodigo();
    }

    @Override
    public EstablecimientoDTO obtenerEstablecimiento(String codigoEstablecimiento) throws Exception {

        System.err.println(establecimientoRepo.findByCodigo(codigoEstablecimiento));

        Optional<Establecimiento> establecimiento = establecimientoRepo.findByCodigo(codigoEstablecimiento);

        return establecimiento.map(value -> new EstablecimientoDTO(
                value.getCodigo(),
                value.getImagenes(),
                value.getDescripcion(),
                value.getNombre(),
                value.getTelefonos(),
                value.getUbicacion(),
                value.getHorarios(),
                value.getCodigoUsuario()
        )).orElse(null);

    }


    @Override
    public String cargarImagenes(ArrayList<String>imagenes, String codigo) throws Exception {

        if (imagenes == null) {
            throw new Exception("Las imagenes no pueden ser nulas");
        }
        if (imagenes.isEmpty()) {
            throw new Exception("Las imagenes no pueden estar vacias");
        }

        Optional<Establecimiento> establecimientoRegistrado = establecimientoRepo.findById(codigo);

        if (establecimientoRegistrado.isEmpty()) {
            throw new Exception("El establecimiento no existe");
        }

        ArrayList<String> imagenesRegistradas = establecimientoRegistrado.get().getImagenes();
        imagenesRegistradas.addAll(imagenes);
        establecimientoRegistrado.get().setImagenes(imagenesRegistradas);
        establecimientoRepo.save(establecimientoRegistrado.get());
        return codigo;
    }

    @Override
    public String crearHorario(ArrayList<Horario> horarios, String codigo) throws Exception {

        if (horarios == null) {
            throw new Exception("Los horarios no pueden ser nulos");
        }
        if (horarios.isEmpty()) {
            throw new Exception("Los horarios no pueden estar vacios");
        }

        Optional<Establecimiento> establecimientoRegistrado = establecimientoRepo.findById(codigo);

        if (establecimientoRegistrado.isEmpty()) {
            throw new Exception("El establecimiento no existe");
        }

        ArrayList<Horario> horariosRegistrados = establecimientoRegistrado.get().getHorarios();
        horariosRegistrados.addAll(horarios);
        establecimientoRegistrado.get().setHorarios(horariosRegistrados);
        establecimientoRepo.save(establecimientoRegistrado.get());
        return codigo;
    }

    @Override
    public void editarHorario(String codigoEstablecimiento) throws Exception {

        Optional<Establecimiento> establecimientoHorario = establecimientoRepo.findById(codigoEstablecimiento);

    }

    @Override
    public String eliminarHorario(String codigoEstablecimiento) throws Exception {
        return null;
    }

    @Override
    public void crearCategoria(String categoria) throws Exception {

    }

    @Override
    public String editarCategoria(String codigoEstablecimiento) throws Exception {
        return null;
    }

    @Override
    public String eliminarCategoria(String codigoEstablecimiento) throws Exception {
        return null;
    }

    @Override
    public void crearTelefono(ArrayList<String> telefonos) throws Exception {

    }

    @Override
    public void editarTelefono(String codigoEstablecimiento) throws Exception {

    }

    @Override
    public String eliminarTelefono(String codigoEstablecimient) throws Exception {
        return null;
    }

    @Override
    public String guardarUbicacion(String codigoEstablecimient) throws Exception {
        return null;
    }

    @Override
    public String eliminarEstablecimiento(String codigoEstablecimient) throws Exception {
        return null;
    }

    @Override
    public String actualizarEstablecimiento(String codigoEstablecimient) throws Exception {
        return null;
    }

    @Override
    public void buscarEstablecimientos(String codigoEstablecimiento) throws Exception {

    }

    @Override
    public Optional<Establecimiento> obtenerLugarDia(String codigoEstablecimiento) throws Exception {
        return Optional.empty();
    }

    @Override
    public float obtenerPromedio(String codigoEstablecimiento) throws Exception {
        Optional<Establecimiento> optionalEstablecimiento = establecimientoRepo.findById(codigoEstablecimiento);
        if (optionalEstablecimiento.isEmpty()) {
            throw new Exception("El establecimiento no existe");
        }

        return optionalEstablecimiento.get().getPromedio();
    }
}
