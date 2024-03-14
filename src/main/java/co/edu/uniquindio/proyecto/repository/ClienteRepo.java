package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.models.documentos.Cliente;
import co.edu.uniquindio.proyecto.models.enums.CategoriaEstablecimiento;
import co.edu.uniquindio.proyecto.models.enums.EstadoCuenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ClienteRepo extends MongoRepository<Cliente, String> {

    Optional<Cliente> findByNickName(String nickName);
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByCodigo(String codigo);
    Optional<ArrayList<String>> findByCodigosFavoritos(String codigo);
    Optional<ArrayList<CategoriaEstablecimiento>> finByHistoricoCategoriasBuscadas(String codigo);
    Optional<Cliente> findByEmailAndContrasena(String email, String contrasena);
    Optional<ArrayList<Cliente>> findAllByEstado(EstadoCuenta estado);
}
