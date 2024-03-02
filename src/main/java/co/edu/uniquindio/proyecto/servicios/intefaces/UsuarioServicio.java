package co.edu.uniquindio.proyecto.servicios.intefaces;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.models.documentos.Usuario;

public interface UsuarioServicio {

    void registrarUsuario(RegistroUsuarioDTO registroUSuarioDTO) throws Exception;
    void eliminarUsuario(String idUsuario) throws Exception;
    void iniciarSesion(InicioSesionDTO inicioSesionDTO) throws Exception;
    void actualizarUsuario(ActualizacionUsuarioDTO actualizacionUsuarioDTO) throws Exception;
    void listarUsuarios() throws Exception;
    void obtenerUsuario(String idUsuario) throws Exception;
    void buscarUsuarios() throws Exception;
    void enviarLinkRecuperacionContrasena(String email) throws Exception;
    void recuperarContrasena(RecuperacionContrasenaDTO recuperacionContrasenaDTO) throws Exception;
}
