package co.edu.uniquindio.proyecto.models.entidades;

import co.edu.uniquindio.proyecto.models.enums.EstadoCuenta;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@AllArgsConstructor
@Builder
public class Cuenta {

    private String email;
    private String contrasenia;
    private EstadoCuenta estado;
    private String nickName;

}
