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
    void editarHorario(String codigoEstablecimiento) throws Exception;
    String eliminarHorario(String codigoEstablecimiento) throws Exception;
    void crearCategoria(String categoria) throws Exception;
    String editarCategoria(String codigoEstablecimiento) throws Exception;
    String eliminarCategoria(String codigoEstablecimiento) throws Exception;
    void crearTelefono(ArrayList<String> telefonos) throws Exception;
    void editarTelefono(String codigoEstablecimiento) throws Exception;
    String eliminarTelefono(String codigoEstablecimiento) throws Exception;
    String guardarUbicacion(String codigoEstablecimiento) throws Exception;
    String eliminarEstablecimiento(String codigoEstablecimiento) throws Exception;
    String actualizarEstablecimiento(String codigoEstablecimiento) throws Exception;
    void buscarEstablecimientos(String codigoEstablecimiento) throws Exception;
    Optional<Establecimiento> obtenerLugarDia(String codigoEstablecimiento) throws Exception;
    float obtenerPromedio(String codigoEstablecimiento) throws Exception;
}
