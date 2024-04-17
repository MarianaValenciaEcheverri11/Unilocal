package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.EstablecimientoDTO;
import co.edu.uniquindio.proyecto.dto.RevisionDTO;
import co.edu.uniquindio.proyecto.models.enums.EstadoPublicacion;
import co.edu.uniquindio.proyecto.servicios.interfaces.RevisionServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class RevisionServicioTest {


    @Autowired
    private RevisionServicio revisionServicio;

    @Test
    public void enviarRevisionTest() throws Exception {
        RevisionDTO revisionDTO = new RevisionDTO(
                "12",
                "12",
                "12",
                EstadoPublicacion.APROBADA,
                LocalDateTime.MAX,
                "12"
                );

        Assertions.assertNotNull(revisionServicio.enviarRevision(revisionDTO));

    }
}
