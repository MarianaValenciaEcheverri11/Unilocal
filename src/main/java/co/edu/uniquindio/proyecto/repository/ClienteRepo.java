package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.models.documentos.Cliente;
import co.edu.uniquindio.proyecto.models.enums.CategoriaEstablecimiento;
import co.edu.uniquindio.proyecto.models.enums.EstadoCuenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepo extends MongoRepository<Cliente, String> {

    @Query("{ 'nickName' : ?0 }")
    Optional<Cliente> findByNickName(String nickName);
    @Query("{ 'email' : ?0 }")
    Optional<Cliente> findByEmail(String email);
    @Query("{ 'codigo' : ?0 }")
    Optional<Cliente> findByCodigo(String codigo);
    @Query("{ 'email' : ?0, 'contrasena' : ?1 }")
    Optional<Cliente> findByEmailAndContrasena(String email, String contrasena);
    @Query("{ 'estado' : ?0  }")
    List<Cliente> findAllByEstado(EstadoCuenta estado);
}