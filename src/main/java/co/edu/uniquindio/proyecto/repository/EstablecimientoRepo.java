package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.dto.RevisionEstablecimientoDTO;
import co.edu.uniquindio.proyecto.models.documentos.Establecimiento;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Repository
public interface EstablecimientoRepo extends MongoRepository<Establecimiento, String> {

    Optional<Establecimiento> findByCodigo(String codigoEstablecimiento);

    Optional<Establecimiento> deleteByCodigo(String codigoEstablecimiento);

    @Aggregation(pipeline = {
            "{ $lookup: { from: 'revisiones', localField: '_id', foreignField: 'codigoEstablecimiento', as: 'revisiones_establecimiento' } }",
            "{ $unwind: '$revisiones_establecimiento' }",
            "{ $match: { 'revisiones_establecimiento.estado': ?0 } }"
    })
    Optional<ArrayList<Establecimiento>> findByRevisionCategoria(String categoria);

    @Aggregation(pipeline = {
            "{ $match: { codigoUsuario: ObjectId(?0) } }",
            "{ $lookup: { from: 'revisiones', localField: '_id', foreignField: 'codigoEstablecimiento', as: 'revisiones_establecimiento' } }",
            "{ $unwind: '$revisiones_establecimiento' }",
            "{ $project: { codigoUsuario: '$codigoUsuario', nombre: '$nombre', descripcion: '$descripcion', imagenes: '$imagenes', estado: '$revisiones_establecimiento.estado' } }"
    })
    Optional<ArrayList<RevisionEstablecimientoDTO>> findByRevisionCliente(String codigoCliente);

    @Aggregation(pipeline = {
            "{ $lookup: { from: 'revisiones', localField: '_id', foreignField: 'codigoEstablecimiento', as: 'revisiones_establecimiento' } }",
            "{ $unwind: '$revisiones_establecimiento' }",
            "{ $match: { 'revisiones_establecimiento.estado': ?0 } }"
    })
    Optional<ArrayList<Establecimiento>> findByEstadoRevision(String estado);

}
