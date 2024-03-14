import co.edu.uniquindio.proyecto.dto.RegistroClienteDTO;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class ClienteServicioTest {

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
