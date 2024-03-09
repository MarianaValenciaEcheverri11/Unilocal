package co.edu.uniquindio.proyecto.servicios.intefaces;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.models.documentos.Usuario;

public interface UsuarioServicio extends CuentaServicio {

    String registrarUsuario(RegistroUsuarioDTO registroUSuarioDTO) throws Exception;
    void marcarLugarFavorito(FavoritoDTO favoritoDTO) throws Exception;
    void eliminarLugarFavorito(FavoritoDTO favoritoDTO) throws Exception;
    void listarFavoritos(String codigoCliente) throws Exception;
    void listarUsuarios() throws Exception;
    void obtenerUsuario(String idUsuario) throws Exception;
    void buscarUsuarios() throws Exception;
    void obtenerHistoricoCategoriasBuscadas(String codigoCliente) throws Exception;
    void obtenerLugarDia() throws Exception;
}