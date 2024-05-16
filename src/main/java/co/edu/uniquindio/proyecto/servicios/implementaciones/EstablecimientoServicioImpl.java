package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.EstablecimientoDTO;
import co.edu.uniquindio.proyecto.dto.RevisionEstablecimientoDTO;
import co.edu.uniquindio.proyecto.models.documentos.Cliente;
import co.edu.uniquindio.proyecto.models.documentos.Establecimiento;
import co.edu.uniquindio.proyecto.repository.ClienteRepo;
import co.edu.uniquindio.proyecto.repository.ComentarioRepo;
import co.edu.uniquindio.proyecto.repository.EstablecimientoRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.EstablecimientoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EstablecimientoServicioImpl implements EstablecimientoServicio {

    private final EstablecimientoRepo establecimientoRepo;
    private final ComentarioRepo comentarioRepo;
    private final ClienteRepo clienteRepo;

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

        establecimiento.get().setPromedio(obtenerPromedio(codigoEstablecimiento));

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

        ArrayList<Establecimiento> establecimientos = establecimientoRepo.findByRevisionCategoria("APROBADA").get();

        for (Establecimiento establecimiento : establecimientos) {
            establecimiento.setPromedio(obtenerPromedio(establecimiento.getCodigo()));
        }

        return establecimientos;
    }

    @Override
    public ArrayList<Establecimiento> listarEstablecimientosPorCategoria(String categoria) {

        ArrayList<Establecimiento> establecimientos = establecimientoRepo.findByRevisionCategoria("APROBADA").get();

        ArrayList<Establecimiento> establecimientosCategoria = new ArrayList<>();

        for (Establecimiento establecimiento : establecimientos) {
            if (establecimiento.getCategoria().equals(categoria)) {
                establecimientosCategoria.add(establecimiento);
            }
            establecimiento.setPromedio(obtenerPromedio(establecimiento.getCodigo()));
        }

        return establecimientosCategoria;
    }

    @Override
    public Establecimiento obtenerEstablecimientoAleatorio() throws Exception {

        ArrayList<Establecimiento> establecimientos = establecimientoRepo.findByRevisionCategoria("APROBADA").get();

        if (establecimientos.isEmpty()) {
            throw new Exception("No hay establecimientos");
        }

        int numeroAleatorio = (int) (Math.random() * establecimientos.size());

        establecimientos.get(numeroAleatorio).setPromedio(obtenerPromedio(establecimientos.get(numeroAleatorio).getCodigo()));

        return establecimientos.get(numeroAleatorio);
    }

    @Override
    public ArrayList<Establecimiento> listarMejoresEstablecimientos() throws Exception {

        ArrayList<Establecimiento> establecimientos = establecimientoRepo.findByRevisionCategoria("APROBADA").get();

        if (establecimientos.isEmpty()) {
            throw new Exception("No hay establecimientos");
        }

        for (Establecimiento establecimiento : establecimientos) {
            establecimiento.setPromedio(obtenerPromedio(establecimiento.getCodigo()));
        }

        establecimientos.sort((e1, e2) -> Float.compare(e2.getPromedio(), e1.getPromedio()));

        return new ArrayList<>(establecimientos.subList(0, Math.min(3, establecimientos.size())));
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

    @Override
    public ArrayList<Establecimiento> listarEstablecimientosPorCalificacion(int pagina) throws Exception {

        ArrayList<Establecimiento> establecimientos = establecimientoRepo.findByRevisionCategoria("APROBADA").get();

        if (establecimientos.isEmpty()) {
            throw new Exception("No hay establecimientos");
        }

        for (Establecimiento establecimiento : establecimientos) {
            establecimiento.setPromedio(obtenerPromedio(establecimiento.getCodigo()));
        }

        establecimientos.sort((e1, e2) -> Float.compare(e2.getPromedio(), e1.getPromedio()));

        int inicio = (pagina - 1) * 5;

        if (inicio >= establecimientos.size()) {
            throw new Exception("No hay establecimientos en la página " + pagina);
        }

        int fin = Math.min(inicio + 5, establecimientos.size());

        return new ArrayList<>(establecimientos.subList(inicio, fin));

    }

    @Override
    public ArrayList<Establecimiento> listarEstablecimientosPorCliente(String codigoCliente) throws Exception {

        ArrayList<Establecimiento> establecimientos = (ArrayList<Establecimiento>) establecimientoRepo.findAll();

        if (establecimientos.isEmpty()) {
            throw new Exception("No hay establecimientos");
        }

        ArrayList<Establecimiento> establecimientosCliente = new ArrayList<>();

        Cliente cliente = clienteRepo.findByCodigo(codigoCliente).orElseThrow(() -> new Exception("El cliente no existe"));
        ArrayList establecimientosFavoritosCliente = cliente.getCodigosFavoritos();

        for (Establecimiento establecimiento : establecimientos) {
            if (establecimientosFavoritosCliente.contains(establecimiento.getCodigo())) {
                establecimientosCliente.add(establecimiento);
            }
            establecimiento.setPromedio(obtenerPromedio(establecimiento.getCodigo()));
        }

        return establecimientosCliente;
    }

    @Override
    public ArrayList<Establecimiento> listarEstablecimientosPorEstadoRevision(String estado) throws Exception {

        ArrayList<Establecimiento> establecimientos = establecimientoRepo.findByRevisionCategoria(estado).get();

        for (Establecimiento establecimiento : establecimientos) {
            establecimiento.setPromedio(obtenerPromedio(establecimiento.getCodigo()));
        }

        return establecimientos;
    }

    @Override
    public ArrayList<RevisionEstablecimientoDTO> listarEstablecimientosPorRevisionesCliente(String codigoCliente) throws Exception {
        ArrayList<RevisionEstablecimientoDTO> establecimientos = establecimientoRepo.findByRevisionCliente(codigoCliente).get();
        return establecimientos;
    }

    public Float obtenerPromedio(String codigoEstablecimiento) {

        Float promedio = 0.0f;

        promedio = comentarioRepo.obtenerPromedio(codigoEstablecimiento);
        if (promedio != null) {
            return promedio;
        }

        return 0f;
    }
}
