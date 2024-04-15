package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.EstablecimientoDTO;
import co.edu.uniquindio.proyecto.models.entidades.Horario;
import co.edu.uniquindio.proyecto.servicios.interfaces.EstablecimientoServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class EstablecimientoServicioTest {

    @Autowired
    private EstablecimientoServicio establecimientoServicio;

    @Test
    public void crearEstablecimientoTest() throws Exception {
        EstablecimientoDTO establecimientoDTO = new EstablecimientoDTO(
                "12",
                null,
                "Hola, me encanta este lugar",
                "12",
                null,
                null,
                null,
                "string"
        );

        Assertions.assertNotNull(establecimientoServicio.crearEstablecimiento(establecimientoDTO));

    }

    @Test
    public void obtenerEstablecimientoTest() throws Exception {

        Assertions.assertNotNull(establecimientoServicio.obtenerEstablecimiento("12"));

    }

}
