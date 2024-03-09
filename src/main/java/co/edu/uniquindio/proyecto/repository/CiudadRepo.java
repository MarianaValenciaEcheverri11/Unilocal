package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.models.entidades.Ciudad;
import co.edu.uniquindio.proyecto.models.entidades.Cuenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepo extends MongoRepository<Ciudad, String> {
}