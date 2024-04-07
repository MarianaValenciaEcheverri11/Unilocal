package co.edu.uniquindio.proyecto.controladores;
import co.edu.uniquindio.proyecto.dto.ImagenDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.ImagenesServicio;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/imagenes")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class ImagenesController {
    private final ImagenesServicio imagenesServicio;

    @PostMapping("/subir")
    public ResponseEntity<MensajeDTO<Map>> subir(@RequestParam("file") MultipartFile imagen)
            throws Exception{
        Map respuesta = imagenesServicio.subirImagen(imagen);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, respuesta ));
    }
    @DeleteMapping("/eliminar")
    public ResponseEntity<MensajeDTO<Map>> eliminar(@RequestBody ImagenDTO imagenDTO) throws
            Exception {
        Map respuesta = imagenesServicio.eliminarImagen(imagenDTO.codigo());
        return ResponseEntity.ok().body(new MensajeDTO<>(false, respuesta));
    }

}
