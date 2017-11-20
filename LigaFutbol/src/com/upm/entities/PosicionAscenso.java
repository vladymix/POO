package com.upm.entities;

public class PosicionAscenso extends Posicion {

    public PosicionAscenso(Posicion posicion) {
        super(posicion);
    }

    public String toString() {
        return "ASC ---> " + super.toString();
    }
}
