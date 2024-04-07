package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.EstablecimientoDTO;
import co.edu.uniquindio.proyecto.models.documentos.Establecimiento;
import co.edu.uniquindio.proyecto.models.entidades.Horario;

import java.util.ArrayList;
import java.util.Optional;

public interface EstablecimientoServicio {

    String crearEstablecimiento(EstablecimientoDTO establecimientoDTO) throws Exception;
    String cargarImagenes(ArrayList<String> imagenes, String codigo) throws Exception;
    String crearHorario(ArrayList<Horario> horarios, String codigo) throws Exception;
    void editarHorario() throws Exception;
    String eliminarHorario() throws Exception;
    void crearCategoria() throws Exception;
    String editarCategoria() throws Exception;
    String eliminarCategoria() throws Exception;
    void crearTelefono() throws Exception;
    void editarTelefono() throws Exception;
    String eliminarTelefono() throws Exception;
    String guardarUbicacion() throws Exception;
    String eliminarEstablecimiento() throws Exception;
    String actualizarEstablecimiento() throws Exception;
    Optional<Establecimiento> listarEstablecimientos() throws Exception;
    Optional<Establecimiento> obtenerEstablecimiento() throws Exception;
    void buscarEstablecimientos() throws Exception;
    void listarEstablecimientosUsuario() throws Exception;
    void listaEstablecimientoPorEstado() throws Exception;
    void listaEstablecimientoPorCategoria() throws Exception;
    void listaEstablecimientoPorUbicacion() throws Exception;
    Optional<Establecimiento> obtenerLugarDia() throws Exception;
    float obtenerPromedio(String codigoEstablecimiento) throws Exception;
}
