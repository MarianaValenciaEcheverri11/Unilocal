package co.edu.uniquindio.proyecto.models.entidades;

public class Ubicacion {

    private double lontitud;
    private double latitud;

    public Ubicacion(double lontitud, double latitud) {
        this.lontitud = lontitud;
        this.latitud = latitud;
    }

    public double getLontitud() {
        return lontitud;
    }

    public void setLontitud(double lontitud) {
        this.lontitud = lontitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
}
