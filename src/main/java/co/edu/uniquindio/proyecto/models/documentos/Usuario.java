package co.edu.uniquindio.proyecto.models.documentos;

import co.edu.uniquindio.proyecto.models.enums.CategoriaEstablecimiento;
import co.edu.uniquindio.proyecto.models.enums.EstadoCuenta;
import co.edu.uniquindio.proyecto.models.enums.Rol;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;

@Document("usuarios")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Usuario extends Cuenta implements Serializable {

    @Id
    @EqualsAndHashCode.Include

    private String cedula;
    private String nombre;
    private String foto;
    private String codigoCiudad;
    private ArrayList<String> codigosFavoritos;
    private Rol rol;
    private ArrayList<CategoriaEstablecimiento> historicoCategoriasBuscadas;

    public Usuario(String email, String contrasenia, EstadoCuenta estado, String nickName) {
        super(email, contrasenia, estado, nickName);
    }
}