package co.edu.uniquindio.proyecto.models;

import java.util.ArrayList;

public class Publicacion {

    private EstadoPublicacion estado;

    private ArrayList<String> codigosRevisiones;

    public Publicacion(EstadoPublicacion estado, ArrayList<String> codigosRevisiones) {
        this.estado = estado;
        this.codigosRevisiones = codigosRevisiones;
    }

    public EstadoPublicacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoPublicacion estado) {
        this.estado = estado;
    }

    public ArrayList<String> getCodigosRevisiones() {
        return codigosRevisiones;
    }

    public void setCodigosRevisiones(ArrayList<String> codigosRevisiones) {
        this.codigosRevisiones = codigosRevisiones;
    }
}
