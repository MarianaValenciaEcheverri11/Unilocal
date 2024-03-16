package co.edu.uniquindio.proyecto.models.entidades;

import co.edu.uniquindio.proyecto.models.enums.EstadoCuenta;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class Cuenta {
    private String email;
    private String contrasena;
    private EstadoCuenta estado;
    private String nickName;
}