package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.models.Establecimiento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstablecimientoRepo extends MongoRepository<Establecimiento, String>{


}
