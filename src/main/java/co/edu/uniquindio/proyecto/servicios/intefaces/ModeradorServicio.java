package co.edu.uniquindio.proyecto.servicios.intefaces;

public interface ModeradorServicio extends CuentaServicio {

    void listarModeradores() throws Exception;
    void obtenerModerador(String idUsuario) throws Exception;
    void buscarModeradores() throws Exception;

}