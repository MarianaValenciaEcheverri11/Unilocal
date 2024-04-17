package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.EstablecimientoDTO;
import co.edu.uniquindio.proyecto.models.documentos.Establecimiento;
import co.edu.uniquindio.proyecto.models.entidades.Horario;

import java.util.ArrayList;
import java.util.Optional;

public interface EstablecimientoServicio {

    String crearEstablecimiento(EstablecimientoDTO establecimientoDTO) throws Exception;
    EstablecimientoDTO obtenerEstablecimiento(String codigoEstablecimiento) throws Exception;
    String actualizarEstablecimiento(String codigoEstablecimiento) throws Exception;
    String eliminarEstablecimiento(String codigoEstablecimiento) throws Exception;

    ArrayList<EstablecimientoDTO> listarEstablecimientos();
    ArrayList<EstablecimientoDTO> listarEstablecimientosPorCategoria(String categoria);


}
