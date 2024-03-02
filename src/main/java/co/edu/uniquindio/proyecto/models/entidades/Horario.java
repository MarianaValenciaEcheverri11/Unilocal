package co.edu.uniquindio.proyecto.models.entidades;

import co.edu.uniquindio.proyecto.models.enums.Dia;

import java.time.LocalDateTime;

public class Horario {

    private LocalDateTime horaApertura;

    private LocalDateTime horaCierre;

    private Dia dia ;

    public Horario(LocalDateTime horaApertura, LocalDateTime horaCierre, Dia dia) {
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.dia = dia;
    }

    public LocalDateTime getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(LocalDateTime horaApertura) {
        this.horaApertura = horaApertura;
    }

    public LocalDateTime getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(LocalDateTime horaCierre) {
        this.horaCierre = horaCierre;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }
}
