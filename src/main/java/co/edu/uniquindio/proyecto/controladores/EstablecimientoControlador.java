package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.EstablecimientoDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.models.documentos.Establecimiento;
import co.edu.uniquindio.proyecto.servicios.interfaces.EstablecimientoServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    public ResponseEntity<Establecimiento> obtenerEstablecimiento(@PathVariable String id) throws Exception {
        return ResponseEntity.ok().body(establecimientoServicio.obtenerEstablecimiento(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarEstablecimiento(@PathVariable String id) throws Exception {
        establecimientoServicio.eliminarEstablecimiento(id);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "establecimiento eliminado correctamente")
        );
    }

    @PutMapping("/actualizar-establecimiento/{id}")
    public ResponseEntity<MensajeDTO<String>> actualizarEstablecimiento(@PathVariable String id, @Valid @RequestBody EstablecimientoDTO establecimientoDTO) throws Exception {
        establecimientoServicio.actualizarEstablecimiento(id, establecimientoDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "establecimiento actualizado correctamente")
        );
    }

    @GetMapping("/listar-establecimientos")
    public ResponseEntity<ArrayList<Establecimiento>> listarEstablecimientos() throws Exception {
        return ResponseEntity.ok().body(establecimientoServicio.listarEstablecimientos());
    }

    @GetMapping("/listar-establecimientos-por-categoria/{categoria}")
    public ResponseEntity<ArrayList<Establecimiento>> listarEstablecimientosPorCategoria(@PathVariable String categoria) throws Exception {
        return ResponseEntity.ok().body(establecimientoServicio.listarEstablecimientosPorCategoria(categoria));
    }

    @GetMapping("/obtener-establecimiento-aleatorio")
    public ResponseEntity<Establecimiento> obtenerEstablecimientoAleatorio() throws Exception {
        return ResponseEntity.ok().body(establecimientoServicio.obtenerEstablecimientoAleatorio());
    }

    @GetMapping("/listar-mejores-establecimientos")
    public ResponseEntity<ArrayList<Establecimiento>> listarMejoresEstablecimientos() throws Exception {
        return ResponseEntity.ok().body(establecimientoServicio.listarMejoresEstablecimientos());
    }

    @GetMapping("/listar-establecimientos-por-calificacion/{pagina}")
    public ResponseEntity<ArrayList<Establecimiento>> listarEstablecimientosPorCalificacion(@PathVariable int pagina) throws Exception {
        return ResponseEntity.ok().body(establecimientoServicio.listarEstablecimientosPorCalificacion(pagina));
    }

    @GetMapping("/listar-establecimientos-por-cliente/{codigoCliente}")
    public ResponseEntity<ArrayList<Establecimiento>> listarEstablecimientosPorCliente(@PathVariable String codigoCliente) throws Exception {
        return ResponseEntity.ok().body(establecimientoServicio.listarEstablecimientosPorCliente(codigoCliente));
    }

    @GetMapping("/listar-establecimientos-por-estado-revision/{estado}")
    public ResponseEntity<ArrayList<Establecimiento>> listarEstablecimientosPorEstadoRevision(@PathVariable String estado) throws Exception {
        return ResponseEntity.ok().body(establecimientoServicio.listarEstablecimientosPorEstadoRevision(estado));
    }
}
