package com.upm.entities;

public class PosicionDescenso extends Posicion {
    @Override
    public String getPreview(IPreviewData previewData) {
        return previewData.preview(this);
    }

    public PosicionDescenso(Posicion posicion) {
        super(posicion);
    }

    public String toString() {
        return "DSC ---> " + super.toString();
    }
}
