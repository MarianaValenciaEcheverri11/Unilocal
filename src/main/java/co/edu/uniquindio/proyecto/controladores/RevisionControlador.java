package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.EstablecimientoDTO;
import co.edu.uniquindio.proyecto.dto.ItemClienteDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.RevisionDTO;
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
    public ResponseEntity<RevisionDTO> obtenerRevision(@PathVariable String id) throws Exception {
        return ResponseEntity.ok().body(revisionServicio.consultarRevisiones(id));
    }

    @GetMapping("/listar-revisiones")
    public ResponseEntity<ArrayList<Revision>> listarRevision() throws Exception {
        return ResponseEntity.ok().body(revisionServicio.obtenerTodasRevisiones());
    }




}
