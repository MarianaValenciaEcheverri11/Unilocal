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

        if (!existeUsuario(comentarioDTO.codigoCliente())) {
            throw new Exception("El usuario no existe");
        }

        if (!existeEstablecimiento(comentarioDTO.codigoEstablecimiento())) {
            throw new Exception("El establecimiento no existe");
        }

        Comentario comentario = new Comentario(
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

    private boolean existeUsuario(String codigo) {
        return clienteRepo.findByCodigo(codigo).isPresent();
    }

    private boolean existeEstablecimiento(String codigo) {
        return establecimientoRepo.findByCodigo(codigo).isPresent();
    }

    public String responderComentario(String codigo, ResponderComentarioDTO responderComentarioDTO) throws Exception {

        Optional<Comentario> comentario = comentarioRepo.findByCodigo(codigo);
        comentario.get().setRespuesta(responderComentarioDTO.ComentarioInsertado());
        comentarioRepo.save(comentario.get());

        Optional<Cliente> cliente = clienteRepo.findByCodigo(comentario.get().getCodigoCliente());
        EmailDTO emailDTO = new EmailDTO(
                cliente.get().getEmail(),
                "Respuesta a su comentario",
                "Se ha respondido a su comentario \n" +
                        "Fecha: " + comentario.get().getFecha() + "\n" +
                        "Valoración: " + comentario.get().getValoracion() + "\n" +
                        "Reseña: " + comentario.get().getResenia() + "\n" +
                        "Respuesta: " + comentario.get().getRespuesta() + "\n" +
                        "Gracias por usar nuestra plataforma."
        );

        emailServicio.enviarEmail(emailDTO);

        return comentario.get().getCodigo();
    }

    public ArrayList<Comentario> listarComentariosPorEstablecimiento(String idEstablecimiento) throws Exception {

        System.out.println(idEstablecimiento);

        Optional<ArrayList<Comentario>> comentarios = comentarioRepo.findByCodigoEstablecimiento(idEstablecimiento);

        if (comentarios.isEmpty()) {
            throw new Exception("No hay comentarios para esta publicación");
        }

        comentarios.get().forEach(
                comentario -> {
                    System.out.println(comentario.getCodigo());
                }
        );

        return comentarios.get();
    }

    @Override
    public String eliminarComentario(String idComentario) throws Exception {
        if (comentarioRepo.findByCodigo(idComentario) == null) {
            throw new Exception("El comentario no existe");
        }
        Optional<Comentario> comentario = comentarioRepo.deleteByCodigo(idComentario);

        return "Comentario con id: " + comentario.get().getCodigo() + " eliminado";

    }

    @Override
    public String actualizarComentario(String codigo, ComentarioDTO comentarioDTO) throws Exception {

        if (comentarioRepo.findByCodigo(codigo).isEmpty()) {
            throw new Exception("El comentario no existe");
        }

        Optional<Comentario> comentario = comentarioRepo.findByCodigo(codigo);

        comentario.get().setRespuesta(comentarioDTO.respuesta());
        comentario.get().setValoracion(comentarioDTO.valoracion());
        comentario.get().setResenia(comentarioDTO.resenia());
        comentarioRepo.save(comentario.get());

        return "Comentario con id: " + comentario.get().getCodigo() + " actualizado";

    }

}
