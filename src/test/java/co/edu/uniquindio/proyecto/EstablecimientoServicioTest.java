package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.EstablecimientoDTO;
import co.edu.uniquindio.proyecto.models.documentos.Establecimiento;
import co.edu.uniquindio.proyecto.models.entidades.Horario;
import co.edu.uniquindio.proyecto.servicios.interfaces.EstablecimientoServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class    EstablecimientoServicioTest {

    @Autowired
    private EstablecimientoServicio establecimientoServicio;

    @Test
    public void crearEstablecimientoTest() throws Exception {
        EstablecimientoDTO establecimientoDTO = new EstablecimientoDTO(
                null,
                "Hola, me encanta este lugar",
                "12",
                null,
                null,
                null,
                "string",
                "string"
        );

        Assertions.assertNotNull(establecimientoServicio.crearEstablecimiento(establecimientoDTO));

        System.err.println(establecimientoServicio.crearEstablecimiento(establecimientoDTO));

    }

    @Test
    public void obtenerEstablecimientoTest() throws Exception {

        Assertions.assertNotNull(establecimientoServicio.obtenerEstablecimiento("662166f6cde027238c25b8e5"));
        System.err.println(establecimientoServicio.obtenerEstablecimiento("662166f6cde027238c25b8e5"));

    }

    @Test
    public void eliminarEstablecimientoTest() throws Exception {

        establecimientoServicio.eliminarEstablecimiento("662166f6cde027238c25b8e5");

        Assertions.assertTrue(establecimientoServicio.obtenerEstablecimiento("662166f6cde027238c25b8e5") == null);

        System.err.println(establecimientoServicio.obtenerEstablecimiento("662166f6cde027238c25b8e5"));

    }

    @Test
    public void listarEstablecimientosTest() throws Exception {

        ArrayList<Establecimiento> lista = (ArrayList<Establecimiento>) establecimientoServicio.listarEstablecimientos();

        System.err.println(lista);

        Assertions.assertTrue(lista.size() > 0);


    }

    @Test
    public void listarEstablecimientosPorCategoriaTest() throws Exception {

        ArrayList<Establecimiento> lista = (ArrayList<Establecimiento>) establecimientoServicio.listarEstablecimientosPorCategoria("RESTAURANTE");

        System.err.println(lista);

        Assertions.assertTrue(lista.size() > 0);

    }

    @Test
    public void actualizarEstablecimientoTest() throws Exception {
        EstablecimientoDTO establecimientoDTO = new EstablecimientoDTO(
                null,
                "Hola, me encanta este lugar",
                "12",
                null,
                null,
                null,
                "string",
                "string"
        );

        Assertions.assertNotNull(establecimientoServicio.actualizarEstablecimiento("12", establecimientoDTO));

        System.err.println(establecimientoServicio.actualizarEstablecimiento("12", establecimientoDTO));

    }

    @Test
    public void obtenerEstablecimientoAleatorioTest() throws Exception {

        Assertions.assertNotNull(establecimientoServicio.obtenerEstablecimientoAleatorio());

        System.err.println(establecimientoServicio.obtenerEstablecimientoAleatorio());

    }

    @Test
    public void listarMejoresEstablecimientosTest() throws Exception {

        ArrayList<Establecimiento> lista = (ArrayList<Establecimiento>) establecimientoServicio.listarMejoresEstablecimientos();

        Assertions.assertTrue(lista.size() > 0);

        System.err.println(lista);

    }

}
