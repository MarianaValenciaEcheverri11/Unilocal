package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.ResponderComentarioDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.ComentarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ComentarioServicioTest {

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Test
    public void crearComentarioTest() throws Exception {
        ComentarioDTO comentarioDTO = new ComentarioDTO(
                "123",
                "2021-10-10",
                5,
                "string",
                "12",
                "Hola, me encanta este lugar",
                ""
        );
        Assertions.assertNotNull(comentarioServicio.crearComentario(comentarioDTO));
    }

    @Test
    public void responderComentarioTest() throws Exception {
        Assertions.assertNotNull(comentarioServicio.responderComentario(new ResponderComentarioDTO("123", "Muchas gracias, tambien me encantas :3")));
    }

    @Test
    public void listarComentariosPorEstablecimientoTest() throws Exception {
        Assertions.assertNotNull(comentarioServicio.listarComentariosPorEstablecimiento("12"));
        System.out.println(comentarioServicio.listarComentariosPorEstablecimiento("12"));
    }

}