package co.edu.uniquindio.proyecto.servicios.intefaces;

import co.edu.uniquindio.proyecto.dto.ActualizacionCuentaDTO;
import co.edu.uniquindio.proyecto.dto.InicioSesionDTO;
import co.edu.uniquindio.proyecto.dto.RecuperacionContrasenaDTO;

public interface CuentaServicio {
    String iniciarSesion(InicioSesionDTO inicioSesionDTO) throws Exception;
    String eliminarCuenta(String idUsuario) throws Exception;
    String actualizarCuenta(ActualizacionCuentaDTO actualizacionCuentaDTO) throws Exception;
    String enviarLinkRecuperacionContrasena(String email) throws Exception;
    String recuperarContrasena(RecuperacionContrasenaDTO recuperacionContrasenaDTO) throws Exception;
}