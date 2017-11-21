package com.upm.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Clasificacion extends ArrayList<Posicion> {

   private IPreviewData previewData;

    public Clasificacion(List<Equipo> equipos) {
        for (Equipo equipo : equipos) {
            Posicion posicion = new Posicion(equipo);
            this.add(posicion);
        }
    }

    public IPreviewData getModePreview() {
        return previewData;
    }

    public String onPreviewData() {
       return previewData.preview(this);
    }

    public void setModePreview(IPreviewData previewData){
        this.previewData = previewData;
    }

    public void ordenarClasificacion() {

        this.sort(Comparator.comparing(Posicion::getPuntos).reversed());

        for (int i = 0; i < this.size(); i++)
            this.set(i, new Posicion(this.get(i)));

        for (int i = 0; i < 3; i++) {
            // ascenso
            this.set(i, new PosicionAscenso(this.get(i)));
            // descenso
            this.set(this.size() - i - 1, new PosicionDescenso(this.get(this.size() - i - 1)));
        }

    }
}
