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
                "2021-10-10",
                5,
                "662148cbc45cab28f631cd48",
                "662148ccc45cab28f631cd4a",
                "Hola, me encanta este lugar",
                ""
        );

        System.err.println(comentarioServicio.crearComentario(comentarioDTO));
        Assertions.assertNotNull(comentarioServicio.crearComentario(comentarioDTO));
    }

    @Test
    public void responderComentarioTest() throws Exception {
        Assertions.assertNotNull(comentarioServicio.responderComentario("123", new ResponderComentarioDTO("Muchas gracias, tambien me encantas :3")));
        System.err.println(comentarioServicio.responderComentario("123", new ResponderComentarioDTO("Muchas gracias, tambien me encantas :3")));
    }

    @Test
    public void listarComentariosPorEstablecimientoTest() throws Exception {
        Assertions.assertNotNull(comentarioServicio.listarComentariosPorEstablecimiento("12"));
        System.err.println(comentarioServicio.listarComentariosPorEstablecimiento("12"));
    }

    @Test
    public void eliminarComentarioTest() throws Exception {
        Assertions.assertNull(comentarioServicio.eliminarComentario("123"));
        System.err.println(comentarioServicio.eliminarComentario("123"));
    }

    @Test
    public void actualizarComentarioTest() throws Exception {
        ComentarioDTO comentarioDTO = new ComentarioDTO(
                "2021-10-10",
                5,
                "123",
                "12",
                "Hola, me encanta este lugar",
                ""
        );
        Assertions.assertNotNull(comentarioServicio.actualizarComentario("12", comentarioDTO));
        System.err.println(comentarioServicio.actualizarComentario("12", comentarioDTO));
    }

}