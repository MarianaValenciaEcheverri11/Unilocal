package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.models.documentos.Revision;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RevisionRepo extends MongoRepository<Revision, String> {

    Optional<Revision> findByCodigo(String codigo);
    Optional<Revision> deleteByCodigo(String codigo);
}
