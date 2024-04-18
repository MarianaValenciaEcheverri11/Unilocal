package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.ResponderComentarioDTO;
import co.edu.uniquindio.proyecto.models.documentos.Comentario;
import co.edu.uniquindio.proyecto.servicios.implementaciones.ComentarioServicioImpl;
import co.edu.uniquindio.proyecto.servicios.interfaces.ComentarioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/comentarios")
public class ComentarioController {

    private final ComentarioServicio comentarioServicio;

//    TODO: Quitar id del DTO
    @PostMapping("/registrar-comentario")
    public ResponseEntity<MensajeDTO<String>> registrarComentario(@Valid @RequestBody ComentarioDTO comentarioDTO) throws Exception {
        comentarioServicio.crearComentario(comentarioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario registrado correctamente")
        );
    }

    @PostMapping("/responder-comentario")
    public ResponseEntity<MensajeDTO<String>> responderComentario(@Valid @RequestBody ResponderComentarioDTO responderComentarioDTO) throws Exception {
        comentarioServicio.responderComentario(responderComentarioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario respondido correctamente")
        );
    }

    @GetMapping("/listar-comentarios-establecimiento/{idEstablecimiento}")
    public ResponseEntity<MensajeDTO<Optional<ArrayList<Comentario>>>> listarComentariosPorEstablecimiento(@PathVariable String idEstablecimiento) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.listarComentariosPorEstablecimiento(idEstablecimiento))
        );
    }

    @DeleteMapping("/eliminar-comentario/{idComentario}")
    public ResponseEntity<MensajeDTO<String>> eliminarComentario(@PathVariable String idComentario) throws Exception {
        comentarioServicio.eliminarComentario(idComentario);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario eliminado correctamente")
        );
    }

//   TODO: AGREGAR ID AL PUT
    @PutMapping("/actualizar-comentario")
    public ResponseEntity<MensajeDTO<String>> actualizarComentario(@Valid @RequestBody ComentarioDTO comentarioDTO) throws Exception {
        comentarioServicio.actualizarComentario(comentarioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario actualizado correctamente")
        );
    }

}
