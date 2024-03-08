package co.edu.uniquindio.proyecto.servicios.intefaces;

import co.edu.uniquindio.proyecto.dto.ActualizacionCuentaDTO;
import co.edu.uniquindio.proyecto.dto.InicioSesionDTO;
import co.edu.uniquindio.proyecto.dto.RecuperacionContrasenaDTO;

public interface CuentaServicio {
    void eliminarCuenta(String idUsuario) throws Exception;
    void iniciarSesion(InicioSesionDTO inicioSesionDTO) throws Exception;
    void actualizarCuenta(ActualizacionCuentaDTO actualizacionCuentaDTO) throws Exception;
    void enviarLinkRecuperacionContrasena(String email) throws Exception;
    void recuperarContrasena(RecuperacionContrasenaDTO recuperacionContrasenaDTO) throws Exception;
}