package com.upm.inplementations;

import com.upm.entities.Clasificacion;
import com.upm.entities.Equipo;
import com.upm.entities.IPreviewData;
import com.upm.entities.Posicion;

public class PreviewAsPdf implements IPreviewData {
    @Override
    public String preview(Equipo equipo) {
        return null;
    }

    @Override
    public String preview(Posicion posicion) {
        return null;
    }

    @Override
    public String preview(Clasificacion clasificacion) {
        return null;
    }
}
