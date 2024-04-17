package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.models.documentos.Establecimiento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstablecimientoRepo extends MongoRepository<Establecimiento, String> {

    Optional<Establecimiento> findByCodigo(String codigoEstablecimiento);

    Optional<Establecimiento> deleteByCodigo(String codigoEstablecimiento);

}
