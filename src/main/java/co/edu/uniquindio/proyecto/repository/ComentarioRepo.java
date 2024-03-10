package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.models.documentos.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ComentarioRepo extends MongoRepository<Comentario, String> {

    Optional<Comentario> findByCodigo(String codigo);
    Optional<Comentario> findByCodigoCliente(String codigoCliente);
    Optional<ArrayList<Comentario>> findByCodigoNegocio(String codigoNegocio);

}
