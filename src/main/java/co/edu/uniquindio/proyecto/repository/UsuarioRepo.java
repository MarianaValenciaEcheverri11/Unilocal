package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends MongoRepository<Usuario, String> {
}
