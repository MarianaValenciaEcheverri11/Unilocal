package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.models.documentos.Cliente;
import co.edu.uniquindio.proyecto.models.enums.CategoriaEstablecimiento;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ClienteServicioTest {

    @Autowired
    private  ClienteServicio clienteServicio;

    @Test
    public void registrarClienteTest() throws Exception {

        RegistroClienteDTO registroClienteDTO = new RegistroClienteDTO(
                "David",
                "coso.png",
                "david11111@gmail.com",
                "david111111",
                "1234",
                "Armenia",
                "Armenia"
                );

        Assertions.assertNotNull(clienteServicio.registrarCliente(registroClienteDTO));
    }

    @Test
    public void marcarLugarFavoritoTest() throws Exception {
        FavoritoDTO favoritoDTO = new FavoritoDTO("23", "1234");
        clienteServicio.marcarLugarFavorito(favoritoDTO);
        ArrayList lista = clienteServicio.listarFavoritos(favoritoDTO.codigoCliente());
        Assertions.assertNotEquals(0, lista.size());
    }

    @Test
    public void eliminarLugarFavoritoTest() throws Exception {
        FavoritoDTO favoritoDTO = new FavoritoDTO("23", "1234");
        Assertions.assertNotNull(clienteServicio.eliminarLugarFavorito(favoritoDTO));
    }
    @Test
    public void listarFavoritosTest() throws Exception {
        String codigoCliente = "1234";
        Assertions.assertNotNull(clienteServicio.listarFavoritos(codigoCliente));
    }

    @Test
    public void listarClientesTest() throws Exception {
        int pagina = 1;
        Assertions.assertNotNull(clienteServicio.listarClientes(pagina));
    }

    @Test
    public void obtenerClienteTest() throws Exception {
        String codigo = "1234";
        Assertions.assertNotNull(clienteServicio.obtenerCliente(codigo));
    }

    @Test
    public void obtenerHistoricoCategoriasBuscadasTest() throws Exception {
        String codigoCliente = "124";
        Assertions.assertNotNull(clienteServicio.obtenerHistoricoCategoriasBuscadas(codigoCliente));
    }

    @Test
    public void registrarCategoriaBuscadasTest() throws Exception {
        RegistroCategoriaBuscadaDTO registroCategoriaBuscadaDTO = new RegistroCategoriaBuscadaDTO("1234", CategoriaEstablecimiento.BAR);
        Assertions.assertNotNull(clienteServicio.registrarCategoriaBuscadas(registroCategoriaBuscadaDTO));
    }
}