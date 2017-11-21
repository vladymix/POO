package com.upm.entities;

public class PosicionAscenso extends Posicion {

    @Override
    public String getPreview(IPreviewData previewData) {
        return previewData.preview(this);
    }

    public PosicionAscenso(Posicion posicion) {
        super(posicion);
    }

    public String toString() {
        return "ASC ---> " + super.toString();
    }
}
