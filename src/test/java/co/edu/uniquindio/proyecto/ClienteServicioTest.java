package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.RegistroClienteDTO;
import co.edu.uniquindio.proyecto.servicios.intefaces.ClienteServicio;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClienteServicioTest {

    @Autowired
    private  ClienteServicio clienteServicio;

    @Test
    public void registrarClienteTest() {

        RegistroClienteDTO registroClienteDTO = new RegistroClienteDTO(
                "123",
                "123",
                "123",
                "123",
                "123",
                "123"
                );
    }

    @Test
    public void listarClientesTest() {

        


    }

}
