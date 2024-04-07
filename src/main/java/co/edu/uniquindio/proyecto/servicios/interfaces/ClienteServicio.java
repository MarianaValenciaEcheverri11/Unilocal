package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.models.enums.CategoriaEstablecimiento;

import java.util.ArrayList;

public interface ClienteServicio extends CuentaServicio {
    String registrarCliente(RegistroClienteDTO registroClienteDTO) throws Exception;
    String marcarLugarFavorito(FavoritoDTO favoritoDTO) throws Exception;
    String eliminarLugarFavorito(FavoritoDTO favoritoDTO) throws Exception;
    ArrayList<String> listarFavoritos(String codigoCliente) throws Exception;
    ArrayList<ItemClienteDTO> listarClientes(int pagina) throws Exception;
    DetalleClienteDTO obtenerCliente(String codigo) throws Exception;
    ArrayList<CategoriaEstablecimiento> obtenerHistoricoCategoriasBuscadas(String codigoCliente) throws Exception;
    String registrarCategoriaBuscadas(RegistroCategoriaBuscadaDTO registroCategoriaBuscadaDTO) throws Exception;
    String subirImagen(ImagenDTO imagenDTO) throws Exception;
}