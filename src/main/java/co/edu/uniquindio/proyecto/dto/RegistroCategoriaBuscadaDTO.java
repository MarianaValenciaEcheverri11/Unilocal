package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.models.enums.CategoriaEstablecimiento;

public record RegistroCategoriaBuscadaDTO(
        String codigoCliente,
        CategoriaEstablecimiento categoriaEstablecimiento
) {
}