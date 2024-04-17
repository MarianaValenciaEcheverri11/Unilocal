package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.ActualizacionCuentaDTO;
import co.edu.uniquindio.proyecto.dto.EstablecimientoDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.RegistroClienteDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.EstablecimientoServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/establecimiento")
public class EstablecimientoControlador {

    private final EstablecimientoServicio establecimientoServicio;

    @PostMapping("/registrar-establecimiento")
    public ResponseEntity<MensajeDTO<String>> crearEstablecimiento(@Valid @RequestBody EstablecimientoDTO establecimientoDTO) throws Exception {
        establecimientoServicio.crearEstablecimiento(establecimientoDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "establecimiento creado correctamente")
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstablecimientoDTO> obtenerEstablecimiento(@PathVariable String id) throws Exception {
        return ResponseEntity.ok().body(establecimientoServicio.obtenerEstablecimiento(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarEstablecimiento(@PathVariable String id) throws Exception {
        establecimientoServicio.eliminarEstablecimiento(id);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "establecimiento eliminado correctamente")
        );
    }

//    @PutMapping("/actualizar-establecimiento/{id}")
//    public ResponseEntity<MensajeDTO<String>> actualizarEstablecimiento(@Valid @RequestBody EstablecimientoDTO establecimientoDTO) throws Exception {
//        establecimientoServicio.actualizarEstablecimiento(id, establecimientoDTO);
//        return ResponseEntity.ok().body( new MensajeDTO<>(false, "establecimiento actualizado correctamente")
//        );
//    }

}
