package co.edu.uniquindio.proyecto.models.documentos;

import co.edu.uniquindio.proyecto.models.enums.EstadoCuenta;

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
}
