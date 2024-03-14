package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.models.documentos.Cliente;
import co.edu.uniquindio.proyecto.models.entidades.Ciudad;
import co.edu.uniquindio.proyecto.models.entidades.Cuenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CiudadRepo extends MongoRepository<Ciudad, String> {

    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByNickName(String nickname);
}