package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.dto.ResponderComentarioDTO;
import co.edu.uniquindio.proyecto.models.documentos.Cliente;
import co.edu.uniquindio.proyecto.models.documentos.Comentario;
import co.edu.uniquindio.proyecto.models.documentos.Establecimiento;
import co.edu.uniquindio.proyecto.repository.ClienteRepo;
import co.edu.uniquindio.proyecto.repository.ComentarioRepo;
import co.edu.uniquindio.proyecto.repository.EstablecimientoRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ComentarioServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentarioRepo comentarioRepo;
    private final ClienteRepo clienteRepo;
    private final EstablecimientoRepo establecimientoRepo;
    private final EmailServicio emailServicio;

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

        Optional<Establecimiento> establecimiento = establecimientoRepo.findByCodigo(comentarioDTO.codigoEstablecimiento());
        Optional<Cliente> cliente = clienteRepo.findByCodigo(establecimiento.get().getCodigoUsuario());

        EmailDTO emailDTO = new EmailDTO(
                cliente.get().getEmail(),
                "Nuevo comentario",
                "Se ha realizado un nuevo comentario en su establecimiento \n" +
                        "Fecha: " + comentarioDTO.fecha() + "\n" +
                        "Valoración: " + comentarioDTO.valoracion() + "\n" +
                        "Reseña: " + comentarioDTO.resenia() + "\n" +
                        "Gracias por usar nuestra plataforma."
        );

        emailServicio.enviarEmail(emailDTO);

        return comentario.getCodigo();
    }

    public String responderComentario(ResponderComentarioDTO responderComentarioDTO) throws Exception {

        if (comentarioRepo.findByCodigo(responderComentarioDTO.idPublicacion()) == null) {
            throw new Exception("El comentario no existe");
        }
        Comentario comentario = comentarioRepo.findByCodigo(responderComentarioDTO.idPublicacion());
        comentario.setRespuesta(responderComentarioDTO.ComentarioInsertado());
        comentarioRepo.save(comentario);

        Optional<Cliente> cliente = clienteRepo.findByCodigo(comentario.getCodigoCliente());
        EmailDTO emailDTO = new EmailDTO(
                cliente.get().getEmail(),
                "Respuesta a su comentario",
                "Se ha respondido a su comentario \n" +
                        "Fecha: " + comentario.getFecha() + "\n" +
                        "Valoración: " + comentario.getValoracion() + "\n" +
                        "Reseña: " + comentario.getResenia() + "\n" +
                        "Respuesta: " + comentario.getRespuesta() + "\n" +
                        "Gracias por usar nuestra plataforma."
        );

        emailServicio.enviarEmail(emailDTO);

        return comentario.getCodigo();
    }

    public Optional<ArrayList<Comentario>> listarComentariosPorEstablecimiento(String idEstablecimiento) throws Exception {

        Optional<ArrayList<Comentario>> comentarios = comentarioRepo.findByCodigoNegocio(idEstablecimiento);

        if (comentarios.isEmpty()) {
            throw new Exception("No hay comentarios para esta publicación");
        }

        return comentarioRepo.findByCodigoNegocio(idEstablecimiento);
    }

}
