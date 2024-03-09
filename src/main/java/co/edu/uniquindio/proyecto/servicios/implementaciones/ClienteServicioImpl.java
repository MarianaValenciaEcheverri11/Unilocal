package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.models.documentos.Establecimiento;
import co.edu.uniquindio.proyecto.models.documentos.Usuario;
import co.edu.uniquindio.proyecto.models.entidades.Publicacion;
import co.edu.uniquindio.proyecto.repository.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.intefaces.UsuarioServicio;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicioImpl implements UsuarioServicio{


    private final UsuarioRepo usuarioRepo;

    public ClienteServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }


    @Override
    public void eliminarCuenta(String idUsuario) throws Exception {

    }

    @Override
    public void iniciarSesion(InicioSesionDTO inicioSesionDTO) throws Exception {

    }

    @Override
    public void actualizarCuenta(ActualizacionCuentaDTO actualizacionCuentaDTO) throws Exception {

    }

    @Override
    public void enviarLinkRecuperacionContrasena(String email) throws Exception {

    }

    @Override
    public void recuperarContrasena(RecuperacionContrasenaDTO recuperacionContrasenaDTO) throws Exception {

    }

    @Override
    public String registrarUsuario(RegistroUsuarioDTO registroUSuarioDTO) throws Exception {

        if(existeUsuario(registroUSuarioDTO.nickname())){
            throw new Exception("El usuario ya existe");
        }

        if (existeEmail(registroUSuarioDTO.email())){
            throw new Exception("El email ya existe");
        }

        Usuario usuario = Usuario.builder()
                .nombre(registroUSuarioDTO.nombre())
                .email(registroUSuarioDTO.email())
                .contrasenia(registroUSuarioDTO.contrasena())
                .build();

        Usuario usuarioRegistrado = usuarioRepo.save(usuario);

        return usuarioRegistrado.getCedula();

    }

    private boolean existeEmail(String email) {

        return usuarioRepo.findByEmail(email).isPresent();
    }

    private boolean existeUsuario(String nickname) {
        return  usuarioRepo.findByNickName(nickname).isPresent();
    }

    @Override
    public void marcarLugarFavorito(FavoritoDTO favoritoDTO) throws Exception {

    }

    @Override
    public void eliminarLugarFavorito(FavoritoDTO favoritoDTO) throws Exception {

    }

    @Override
    public void listarFavoritos(String codigoCliente) throws Exception {

    }

    @Override
    public void listarUsuarios() throws Exception {

    }

    @Override
    public void obtenerUsuario(String idUsuario) throws Exception {

    }

    @Override
    public void buscarUsuarios() throws Exception {

    }

    @Override
    public void obtenerHistoricoCategoriasBuscadas(String codigoCliente) throws Exception {

    }

    @Override
    public void obtenerLugarDia() throws Exception {

    }
}
