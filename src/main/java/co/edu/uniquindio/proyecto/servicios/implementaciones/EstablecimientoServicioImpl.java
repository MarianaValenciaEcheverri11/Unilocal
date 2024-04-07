package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.EstablecimientoDTO;
import co.edu.uniquindio.proyecto.models.documentos.Establecimiento;
import co.edu.uniquindio.proyecto.models.entidades.Horario;
import co.edu.uniquindio.proyecto.repository.EstablecimientoRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.EstablecimientoServicio;

import java.util.ArrayList;
import java.util.Optional;

public class EstablecimientoServicioImpl implements EstablecimientoServicio {

    private final EstablecimientoRepo establecimientoRepo;

    public EstablecimientoServicioImpl(EstablecimientoRepo establecimientoRepo) {
        this.establecimientoRepo = establecimientoRepo;
    }

    @Override
    public String crearEstablecimiento(EstablecimientoDTO establecimientoDTO) throws Exception {

        if (establecimientoDTO == null) {
            throw new Exception("El establecimiento no puede ser nulo");
        }
        if (establecimientoRepo.findById(establecimientoDTO.codigo()).isPresent()) {
            throw new Exception("El establecimiento ya existe");
        }


        Establecimiento establecimiento =        Establecimiento.builder()
                .codigo(establecimientoDTO.codigo())
                .imagenes(establecimientoDTO.imagenes())
                .descripcion(establecimientoDTO.descripcion())
                .nombre(establecimientoDTO.nombre())
                .telefonos(establecimientoDTO.telefonos())
                .ubicacion(establecimientoDTO.ubicacion())
                .horarios(establecimientoDTO.horarios())
                .codigoUsuario(establecimientoDTO.codigoUsuario())
                .build();

        Establecimiento establecimientoRegistrado = establecimientoRepo.save(establecimiento);

        return establecimiento.getCodigo();
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
    public void editarHorario() throws Exception {

    }

    @Override
    public String eliminarHorario() throws Exception {
        return null;
    }

    @Override
    public void crearCategoria() throws Exception {

    }

    @Override
    public String editarCategoria() throws Exception {
        return null;
    }

    @Override
    public String eliminarCategoria() throws Exception {
        return null;
    }

    @Override
    public void crearTelefono() throws Exception {

    }

    @Override
    public void editarTelefono() throws Exception {

    }

    @Override
    public String eliminarTelefono() throws Exception {
        return null;
    }

    @Override
    public String guardarUbicacion() throws Exception {
        return null;
    }

    @Override
    public String eliminarEstablecimiento() throws Exception {
        return null;
    }

    @Override
    public String actualizarEstablecimiento() throws Exception {
        return null;
    }

    @Override
    public Optional<Establecimiento> listarEstablecimientos() throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<Establecimiento> obtenerEstablecimiento() throws Exception {
        return Optional.empty();
    }

    @Override
    public void buscarEstablecimientos() throws Exception {

    }

    @Override
    public void listarEstablecimientosUsuario() throws Exception {

    }

    @Override
    public void listaEstablecimientoPorEstado() throws Exception {

    }

    @Override
    public void listaEstablecimientoPorCategoria() throws Exception {

    }

    @Override
    public void listaEstablecimientoPorUbicacion() throws Exception {

    }

    @Override
    public Optional<Establecimiento> obtenerLugarDia() throws Exception {
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
