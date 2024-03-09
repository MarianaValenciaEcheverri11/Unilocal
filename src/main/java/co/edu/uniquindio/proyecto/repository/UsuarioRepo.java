package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.models.documentos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends MongoRepository<Usuario, String> {

    Optional<Usuario> findByNickName(String nickName);
    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByEmailAndNickName(String email, String nickName);

    Usuario findByCedula(String cedula);
    Usuario findByEmailAndContrasenia(String email, String contrasenia);
    Usuario findByNickNameAndContrasenia(String nickName, String contrasenia);
    Usuario findByEmailAndCodigoCiudad(String email, String codigoCiudad);
    Usuario findByNickNameAndCodigoCiudad(String nickName, String codigoCiudad);
    Usuario findByEmailAndCodigoCiudadAndRol(String email, String codigoCiudad, String rol);
    Usuario findByNickNameAndCodigoCiudadAndRol(String nickName, String codigoCiudad, String rol);
    Usuario findByEmailAndCodigoCiudadAndRolAndEstado(String email, String codigoCiudad, String rol, String estado);
    Usuario findByNickNameAndCodigoCiudadAndRolAndEstado(String nickName, String codigoCiudad, String rol, String estado);
    Usuario findByEmailAndCodigoCiudadAndRolAndEstadoAndContrasenia(String email, String codigoCiudad, String rol, String estado, String contrasenia);
    Usuario findByNickNameAndCodigoCiudadAndRolAndEstadoAndContrasenia(String nickName, String codigoCiudad, String rol, String estado, String contrasenia);
    Usuario findByEmailAndCodigoCiudadAndRolAndEstadoAndContraseniaAndNombre(String email, String codigoCiudad, String rol, String estado, String contrasenia, String nombre);
    Usuario findByNickNameAndCodigoCiudadAndRolAndEstadoAndContraseniaAndNombre(String nickName, String codigoCiudad, String rol, String estado, String contrasenia, String nombre);
    Usuario findByEmailAndCodigoCiudadAndRolAndEstadoAndContraseniaAndNombreAndCedula(String email, String codigoCiudad, String rol, String estado, String contrasenia, String nombre, String cedula);
    Usuario findByNickNameAndCodigoCiudadAndRolAndEstadoAndContraseniaAndNombreAndCedula(String nickName, String codigoCiudad, String rol, String estado, String contrasenia, String nombre, String cedula);
    Usuario findByEmailAndCodigoCiudadAndRolAndEstadoAndContraseniaAndNombreAndCedulaAndFoto(String email, String codigoCiudad, String rol, String estado, String contrasenia, String nombre, String cedula, String foto);



}
