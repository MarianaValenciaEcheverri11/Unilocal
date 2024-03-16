package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.DetalleClienteDTO;
import co.edu.uniquindio.proyecto.models.documentos.Comentario;
import co.edu.uniquindio.proyecto.models.enums.CategoriaEstablecimiento;
import co.edu.uniquindio.proyecto.repository.ComentarioRepo;
import co.edu.uniquindio.proyecto.servicios.intefaces.ClienteServicio;
import co.edu.uniquindio.proyecto.servicios.intefaces.ComentarioServicio;
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
        if (comentarioRepo.findByCodigo(comentarioDTO.codigoPublicacion()).isPresent()) {
            throw new Exception("El comentario ya existe");
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

    public String responderComentario(ComentarioDTO comentarioDTO) throws Exception {
        if (comentarioDTO == null) {
            throw new Exception("El comentario no puede ser nulo");
        }
        if (comentarioRepo.findByCodigo(comentarioDTO.codigoPublicacion()).isEmpty()) {
            throw new Exception("El comentario no existe");
        }
        Comentario comentario = comentarioRepo.findByCodigo(comentarioDTO.codigoPublicacion()).get();
        comentario.setRespuesta(comentarioDTO.respuesta());
        comentarioRepo.save(comentario);

        return comentario.getCodigo();
    }

    public Optional<ArrayList<Comentario>> listarComentariosPorPublicacion(String idPublicacion) throws Exception {

        Optional<ArrayList<Comentario>> comentarios = comentarioRepo.findByCodigoNegocio(idPublicacion);

        if (comentarios.isEmpty()) {
            throw new Exception("No hay comentarios para esta publicación");
        }

        List<ComentarioDTO> respuesta = new  ArrayList<>();


        for (Comentario comentario : comentarios.get()) {

        DetalleClienteDTO cliente = clienteServicio.obtenerCliente(comentario.getCodigoCliente());

            respuesta.add(new ComentarioDTO(
                comentario.getCodigo(),
                comentario.getFecha(),
                comentario.getValoracion(),
                comentario.getRespuesta(),
                comentario.getResenia(),
                cliente.nombre(),
                cliente.foto()
            ));
        }


        return comentarioRepo.findByCodigoNegocio(idPublicacion);
    }

}
