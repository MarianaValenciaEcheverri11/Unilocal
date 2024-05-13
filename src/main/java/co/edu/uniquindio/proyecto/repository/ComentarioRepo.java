package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.dto.ItemComentarioDTO;
import co.edu.uniquindio.proyecto.models.documentos.Comentario;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ComentarioRepo extends MongoRepository<Comentario, String> {

    Optional<Comentario> findByCodigo(String codigo);
    Optional<ArrayList<Comentario>> findByCodigoEstablecimiento(String codigoEstablecimiento);
    Optional<Comentario> deleteByCodigo(String codigo);

    @Aggregation({"{$match : {codigoEstablecimiento : ?0}}",
            "{$lookup:{from: 'clientes', localField: 'codigoCliente', foreignField: '_id', as: 'cliente'}}",
            "{$unwind: '$cliente'}",
            "{$project : { codigoComentario:'$_id', codigoUsuario:'$cliente._id', nombreUsuario: '$cliente.nickName', fotoUsuario: '$cliente.foto', resenia: '$resenia', respuesta: '$respuesta', valoracion: '$valoracion'}}"})
    Optional<ArrayList<ItemComentarioDTO>> listarComentarios(String codigoNegocio);

    @Aggregation({"{$match : {codigoEstablecimiento : ?0}}",
            "{$group: {_id: null, promedio: {$avg: '$valoracion'}}}", "{$project: {promedio: 1, _id: 0}}"})
    Float obtenerPromedio(String codigoEstablecimiento);

}
