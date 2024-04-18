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

    }

    @Test
    public void obtenerEstablecimientoTest() throws Exception {

        Assertions.assertNotNull(establecimientoServicio.obtenerEstablecimiento("12"));

    }

    @Test
    public void eliminarEstablecimientoTest() throws Exception {

        establecimientoServicio.eliminarEstablecimiento("12");

        Assertions.assertTrue(establecimientoServicio.obtenerEstablecimiento("12") == null);

    }

    @Test
    public void listarEstablecimientosTest() throws Exception {

        ArrayList<Establecimiento> lista = (ArrayList<Establecimiento>) establecimientoServicio.listarEstablecimientos();

        System.out.println(lista);

        Assertions.assertTrue(lista.size() > 0);

    }

    @Test
    public void listarEstablecimientosPorCategoriaTest() throws Exception {

        ArrayList<Establecimiento> lista = (ArrayList<Establecimiento>) establecimientoServicio.listarEstablecimientosPorCategoria("12");

        System.out.println(lista);

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

    }


}
