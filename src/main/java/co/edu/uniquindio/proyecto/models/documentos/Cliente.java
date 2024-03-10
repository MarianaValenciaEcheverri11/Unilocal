package co.edu.uniquindio.proyecto.models.documentos;

import co.edu.uniquindio.proyecto.models.entidades.Cuenta;
import co.edu.uniquindio.proyecto.models.enums.CategoriaEstablecimiento;
import co.edu.uniquindio.proyecto.models.enums.EstadoCuenta;
import co.edu.uniquindio.proyecto.models.enums.Rol;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;

@Document("clientes")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Cliente extends Cuenta implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String nombre;
    private String foto;
    private String codigoCiudad;
    private ArrayList<String> codigosFavoritos;
    private Rol rol;
    private ArrayList<CategoriaEstablecimiento> historicoCategoriasBuscadas;

    @Builder
    public Cliente(String email, String contrasenia, EstadoCuenta estado, java.lang.String nickName, java.lang.String codigo, java.lang.String nombre, java.lang.String foto, java.lang.String codigoCiudad, ArrayList<String> codigosFavoritos, Rol rol, ArrayList<CategoriaEstablecimiento> historicoCategoriasBuscadas) {
        super(email, contrasenia, estado, nickName);
        this.codigo = codigo;
        this.nombre = nombre;
        this.foto = foto;
        this.codigoCiudad = codigoCiudad;
        this.codigosFavoritos = codigosFavoritos;
        this.rol = rol;
        this.historicoCategoriasBuscadas = historicoCategoriasBuscadas;
    }
}