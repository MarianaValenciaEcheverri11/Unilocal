package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.models.documentos.Cliente;
import co.edu.uniquindio.proyecto.models.enums.CategoriaEstablecimiento;
import co.edu.uniquindio.proyecto.models.enums.EstadoCuenta;
import co.edu.uniquindio.proyecto.models.enums.Rol;
import co.edu.uniquindio.proyecto.repository.ClienteRepo;
import co.edu.uniquindio.proyecto.servicios.intefaces.ClienteServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
                "Armenia"
                );

        Assertions.assertNotNull(clienteServicio.registrarCliente(registroClienteDTO));
    }


    @Test
    public void marcarLugarFavoritoTest() throws Exception {
        FavoritoDTO favoritoDTO = new FavoritoDTO("123", "65fb83a3d11c7716241bd9d6");

        clienteServicio.marcarLugarFavorito(favoritoDTO);

        ArrayList lista = clienteServicio.listarFavoritos(favoritoDTO.codigoCliente());

        Assertions.assertNotEquals(0, lista.size());
    }

    /*

    @Test
    public void eliminarLugarFavoritoTest() throws Exception {
        FavoritoDTO favoritoDTO = new FavoritoDTO("123", "123");
        Optional<Cliente> optionalCliente = clienteRepositorio.findByCodigo(favoritoDTO.codigoCliente());
        ArrayList<String> codigosFavoritos = optionalCliente.get().getCodigosFavoritos();
        Assertions.assertNotNull(codigosFavoritos.contains(favoritoDTO.codigoPublicacion()));
        codigosFavoritos.remove(favoritoDTO.codigoPublicacion());
        Cliente cliente = Cliente.builder()
                .codigo(favoritoDTO.codigoCliente())
                .codigosFavoritos(codigosFavoritos)
                .build();
        String resultado = clienteServicio.eliminarLugarFavorito(favoritoDTO);
        Assertions.assertNotNull(resultado);
    }


    @Test
    public void listarFavoritosTest() throws Exception {
        String codigoCliente = "123";
        when(clienteRepositorio.findByCodigo(codigoCliente).get().getCodigosFavoritos()).thenReturn(new ArrayList<>());
        Optional<ArrayList<String>> resultado = clienteServicio.listarFavoritos(codigoCliente);
        Assertions.assertNotNull(resultado);
    }

    @Test
    public void listarClientesTest() throws Exception {
        int pagina = 1;
        when(clienteRepositorio.findAllByEstado(EstadoCuenta.ACTIVA)).thenReturn(Optional.of(new ArrayList<Cliente>()));
        ArrayList<ItemClienteDTO> resultado = clienteServicio.listarClientes(pagina);
        Assertions.assertNotNull(resultado);
    }

    @Test
    public void obtenerClienteTest() throws Exception {
        String codigo = "123";
        Cliente cliente = Cliente.builder()
                .codigo(codigo)
                .build();
        when(clienteRepositorio.findByCodigo(codigo)).thenReturn(Optional.ofNullable(cliente));
        DetalleClienteDTO resultado = clienteServicio.obtenerCliente(codigo);
        Assertions.assertNotNull(resultado);
    }

    @Test
    public void obtenerHistoricoCategoriasBuscadasTest() throws Exception {
        String codigoCliente = "123";
        when(clienteServicio.obtenerHistoricoCategoriasBuscadas(codigoCliente)).thenReturn(Optional.of(new ArrayList<>()));
        Optional<ArrayList<CategoriaEstablecimiento>> resultado = clienteServicio.obtenerHistoricoCategoriasBuscadas(codigoCliente);
        Assertions.assertNotNull(resultado);
    }

    @Test
    public void registrarCategoriaBuscadasTest() throws Exception {
        RegistroCategoriaBuscadaDTO registroCategoriaBuscadaDTO = new RegistroCategoriaBuscadaDTO("123", CategoriaEstablecimiento.BAR);
        when(clienteServicio.registrarCategoriaBuscadas(any())).thenReturn("Categoría registrada como buscada");
        String resultado = clienteServicio.registrarCategoriaBuscadas(registroCategoriaBuscadaDTO);
        Assertions.assertNotNull(resultado);
    } */
}