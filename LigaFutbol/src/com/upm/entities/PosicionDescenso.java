package com.upm.entities;

public class PosicionDescenso extends Posicion {
    public PosicionDescenso(Posicion posicion) {
        super(posicion);
    }

    public String toString() {
        return "DSC ---> " + super.toString();
    }
}
