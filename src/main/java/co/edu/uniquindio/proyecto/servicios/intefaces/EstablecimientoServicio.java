package co.edu.uniquindio.proyecto.servicios.intefaces;

import co.edu.uniquindio.proyecto.models.documentos.Establecimiento;

import java.util.Optional;

public interface EstablecimientoServicio {

    void crearEstablecimiento();
    void eliminarEstablecimiento();
    void actualizarEstablecimiento();
    void listarEstablecimientos();
    void obtenerEstablecimiento();
    void buscarEstablecimientos();
    void listarEstablecimientosUsuario();
    void listaEstablecimientoPorEstado();
    void listaEstablecimientoPorCategoria();
    void listaEstablecimientoPorUbicacion();
    Optional<Establecimiento> obtenerLugarDia() throws Exception;

}
