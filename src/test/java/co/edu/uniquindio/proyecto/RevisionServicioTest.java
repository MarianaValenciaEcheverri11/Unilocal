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
                "662144f7c45cab28f631cd37",
                "Por favor corregir la ubicacion",
                EstadoPublicacion.APROBADA,
                "2021-06-01",
                "662144f6c45cab28f631cd36"
                );

        Assertions.assertNotNull(revisionServicio.enviarRevision(revisionDTO));

        System.err.println(revisionServicio.enviarRevision(revisionDTO));

    }

    @Test
    public void obtenerRevisionTest() throws Exception {

        Assertions.assertNotNull(revisionServicio.consultarRevisiones("66214860d856290dc108f2e2"));
        System.out.println(revisionServicio.consultarRevisiones("66214860d856290dc108f2e2"));

    }

    @Test
    public void obtenerTodasRevisionesTest() throws Exception {

        Assertions.assertNotNull(revisionServicio.obtenerTodasRevisiones());

        System.out.println(revisionServicio.obtenerTodasRevisiones());

    }

    @Test
    public void actualizarEstadoRevisionTest() throws Exception {
        RevisionDTO revisionDTO = new RevisionDTO(
                "662148ccc45cab28f631cd4a",
                "Por favor corregir la ubicacion",
                EstadoPublicacion.RECHAZADA,
                "2021-06-01",
                "662144f6c45cab28f631cd36"
        );

        Assertions.assertNotNull(revisionServicio.cambiarEstadoRevision("66214860d856290dc108f2e2", revisionDTO));

        System.err.println(revisionServicio.cambiarEstadoRevision("66214860d856290dc108f2e2", revisionDTO));

    }


}
