package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.DetalleClienteDTO;
import co.edu.uniquindio.proyecto.dto.ResponderComentarioDTO;
import co.edu.uniquindio.proyecto.models.documentos.Comentario;
import co.edu.uniquindio.proyecto.repository.ComentarioRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.ComentarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentarioRepo comentarioRepo;
    private final ClienteServicio clienteServicio;

    public String crearComentario(ComentarioDTO comentarioDTO) throws Exception {
        if (comentarioDTO == null) {
            throw new Exception("El comentario no puede ser nulo");
        }
        Comentario comentario = new Comentario(
                comentarioDTO.codigoPublicacion(),
                comentarioDTO.fecha(),
                comentarioDTO.valoracion(),
                comentarioDTO.codigoCliente(),
                comentarioDTO.codigoEstablecimiento(),
                comentarioDTO.resenia(),
                comentarioDTO.respuesta());
        comentarioRepo.save(comentario);

        return comentario.getCodigo();
    }

    public String responderComentario(ResponderComentarioDTO responderComentarioDTO) throws Exception {

        if (comentarioRepo.findByCodigo(responderComentarioDTO.idPublicacion()) == null) {
            throw new Exception("El comentario no existe");
        }
        Comentario comentario = comentarioRepo.findByCodigo(responderComentarioDTO.idPublicacion());
        comentario.setRespuesta(responderComentarioDTO.ComentarioInsertado());
        comentarioRepo.save(comentario);

        return comentario.getCodigo();
    }

    public Optional<ArrayList<Comentario>> listarComentariosPorEstablecimiento(String idEstablecimiento) throws Exception {

        Optional<ArrayList<Comentario>> comentarios = comentarioRepo.findByCodigoNegocio(idEstablecimiento);

        if (comentarios.isEmpty()) {
            throw new Exception("No hay comentarios para esta publicación");
        }

//        List<ComentarioDTO> respuesta = new  ArrayList<>();
//        for (Comentario comentario : comentarios.get()) {
//
//        DetalleClienteDTO cliente = clienteServicio.obtenerCliente(comentario.getCodigoCliente());
//            respuesta.add(new ComentarioDTO(
//                comentario.getCodigo(),
//                comentario.getFecha(),
//                comentario.getValoracion(),
//                comentario.getRespuesta(),
//                comentario.getResenia(),
//                cliente.nombre(),
//                cliente.foto()
//            ));
//        }

        return comentarioRepo.findByCodigoNegocio(idEstablecimiento);
    }

}
