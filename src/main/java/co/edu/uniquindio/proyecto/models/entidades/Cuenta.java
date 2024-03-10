package co.edu.uniquindio.proyecto.models.entidades;

import co.edu.uniquindio.proyecto.models.enums.EstadoCuenta;
import lombok.*;

@AllArgsConstructor
@Builder
public class Cuenta {
    private String email;
    private String contrasena;
    private EstadoCuenta estado;
    private String nickName;
}