package co.edu.uniquindio.proyecto.models.entidades;

import co.edu.uniquindio.proyecto.models.enums.EstadoCuenta;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@NoArgsConstructor
public class Cuenta {

    private String email;
    private String contrasenia;
    private EstadoCuenta estado;
    private String nickName;

    public Cuenta(String email, String contrasenia, EstadoCuenta estado, String nickName) {
        this.email = email;
        this.contrasenia = contrasenia;
        this.estado = estado;
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public EstadoCuenta getEstado() {
        return estado;
    }

    public void setEstado(EstadoCuenta estado) {
        this.estado = estado;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Document("ciudades")
    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)

    public static class Ciudad implements Serializable {

        @Id
        @EqualsAndHashCode.Include

        private String codigo;
        private String nombre;




    }
}
