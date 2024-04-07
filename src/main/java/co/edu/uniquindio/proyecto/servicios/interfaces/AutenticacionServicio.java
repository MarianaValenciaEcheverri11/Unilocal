package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.InicioSesionDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;

public interface AutenticacionServicio {
    TokenDTO iniciarSesionCliente(InicioSesionDTO loginDTO);
}
