package co.edu.uniquindio.proyecto.models.entidades;

public class Ubicacion {

    private double longitud;
    private double latitud;

    public Ubicacion(double longitud, double latitud) {
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double lontitud) {
        this.longitud = lontitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
}
