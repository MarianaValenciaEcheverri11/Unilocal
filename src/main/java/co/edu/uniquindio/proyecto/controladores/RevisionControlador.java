package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.models.documentos.Establecimiento;
import co.edu.uniquindio.proyecto.models.documentos.Revision;
import co.edu.uniquindio.proyecto.servicios.interfaces.RevisionServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/revision")
public class RevisionControlador {

    private final RevisionServicio revisionServicio;

    @PostMapping("/registrar-revision")
    public ResponseEntity<MensajeDTO<String>> crearRevision(@Valid @RequestBody RevisionDTO revisionDTO) throws Exception {
        revisionServicio.enviarRevision(revisionDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "revision creada correctamente")
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemRevisionDTO> obtenerRevision(@PathVariable String id) throws Exception {
        return ResponseEntity.ok().body(revisionServicio.consultarRevisiones(id));
    }

    @GetMapping("/listar-revisiones")
    public ResponseEntity<ArrayList<Revision>> listarRevision() throws Exception {
        return ResponseEntity.ok().body(revisionServicio.obtenerTodasRevisiones());
    }

    @PutMapping("/actualizar-revision/{id}")
    public ResponseEntity<MensajeDTO<String>> actualizarEstadoRevision(@PathVariable String id, @Valid @RequestBody ActualizarEstadoRevisionDTO actualizarEstadoRevisionDTO) throws Exception {
        revisionServicio.cambiarEstadoRevision(id, actualizarEstadoRevisionDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "revision actualizada correctamente")
        );
    }

    @GetMapping("/listar-revisiones-codigo-establecimiento/{codigoEstablecimiento}")
    public ResponseEntity<ItemRevisionDTO> listarRevision(@PathVariable String codigoEstablecimiento) throws Exception {
        return ResponseEntity.ok().body(revisionServicio.obtenerRevisionPorCodigoEstablecimiento(codigoEstablecimiento));
    }
}
