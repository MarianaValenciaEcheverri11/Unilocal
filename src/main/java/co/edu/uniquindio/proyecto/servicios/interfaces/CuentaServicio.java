package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ActualizacionCuentaDTO;
import co.edu.uniquindio.proyecto.dto.InicioSesionDTO;
import co.edu.uniquindio.proyecto.dto.RecuperacionContrasenaDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;

public interface CuentaServicio {
    TokenDTO iniciarSesionCliente(InicioSesionDTO inicioSesionDTO) throws Exception;
    TokenDTO iniciarSesionModerador(InicioSesionDTO inicioSesionDTO) throws Exception;
    String eliminarCuenta(String idUsuario) throws Exception;
    String actualizarCuenta(ActualizacionCuentaDTO actualizacionCuentaDTO) throws Exception;
    String enviarLinkRecuperacionContrasena(String email) throws Exception;
    String recuperarContrasena(RecuperacionContrasenaDTO recuperacionContrasenaDTO) throws Exception;
}