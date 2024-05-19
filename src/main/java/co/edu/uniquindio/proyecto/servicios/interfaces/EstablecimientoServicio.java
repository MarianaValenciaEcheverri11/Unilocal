package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.EstablecimientoDTO;
import co.edu.uniquindio.proyecto.dto.RevisionEstablecimientoDTO;
import co.edu.uniquindio.proyecto.models.documentos.Establecimiento;

import java.util.ArrayList;

public interface EstablecimientoServicio {

    ArrayList<Establecimiento> listarEstablecimientosPorCalificacion(int pagina) throws Exception;
    String crearEstablecimiento(EstablecimientoDTO establecimientoDTO) throws Exception;
    Establecimiento obtenerEstablecimiento(String codigoEstablecimiento) throws Exception;
    String actualizarEstablecimiento(String codigo, EstablecimientoDTO establecimientoDTO) throws Exception;
    String eliminarEstablecimiento(String codigoEstablecimiento) throws Exception;
    ArrayList<Establecimiento> listarEstablecimientos() throws Exception;
    ArrayList<Establecimiento> listarEstablecimientosPorCategoria(String categoria) throws Exception;
    Establecimiento listarEstablecimientosPorCodigo(String codigo) throws Exception;
    ArrayList<Establecimiento> listarEstablecimientosPorCliente(String codigoCliente) throws Exception;
    Establecimiento obtenerEstablecimientoAleatorio() throws Exception;
    ArrayList<Establecimiento> listarMejoresEstablecimientos() throws Exception;
    ArrayList<Establecimiento> listarEstablecimientosPorEstadoRevision(String estado) throws Exception;
    ArrayList<RevisionEstablecimientoDTO> listarEstablecimientosPorRevisionesCliente(String codigoCliente) throws Exception;

}
