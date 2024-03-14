package co.edu.uniquindio.proyecto.servicios.intefaces;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.models.documentos.Cliente;
import co.edu.uniquindio.proyecto.models.documentos.Establecimiento;
import co.edu.uniquindio.proyecto.models.enums.CategoriaEstablecimiento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ClienteServicio extends CuentaServicio {
    String registrarCliente(RegistroClienteDTO registroClienteDTO) throws Exception;
    String marcarLugarFavorito(FavoritoDTO favoritoDTO) throws Exception;
    String eliminarLugarFavorito(FavoritoDTO favoritoDTO) throws Exception;
    Optional<ArrayList<String>> listarFavoritos(String codigoCliente) throws Exception;
    ArrayList<ItemClienteDTO> listarClientes(int pagina) throws Exception;
    DetalleClienteDTO obtenerCliente(String codigo) throws Exception;
    Optional<ArrayList<CategoriaEstablecimiento>> obtenerHistoricoCategoriasBuscadas(String codigoCliente) throws Exception;
    String registrarCategoriaBuscadas(RegistroCategoriaBuscadaDTO registroCategoriaBuscadaDTO) throws Exception;
}