package com.upm.inplementations;

import com.upm.entities.Clasificacion;
import com.upm.entities.Equipo;
import com.upm.entities.IPreviewData;
import com.upm.entities.Posicion;

public class PreviewAsString implements IPreviewData {
    @Override
    public String preview(Equipo equipo) {
        return equipo.getName();
    }

    @Override
    public String preview(Posicion posicion) {
        return posicion.toString()+ preview(posicion.getEquipo()) + " pts "+ posicion.getPuntos() + " PG "+ posicion.getPartidos_ganados()+" PE " + posicion.getPartidos_empatados() + " PP "+posicion.getPartidos_perdidos();

    }

    @Override
    public String preview(Clasificacion clasificacion) {
        String msg = "";
        for (Posicion posicion : clasificacion)
            msg += preview(posicion) + "\n";

        return msg;
    }
}
