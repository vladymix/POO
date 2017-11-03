package com.upm.entities;

public class Clasificacion implements Comparable<Clasificacion> {

    private  int puntos = 0;

    private  int posicion = 0;

    @Override
    public int compareTo(Clasificacion other) {

        if(other.getPuntos()==this.getPuntos()){
            return 0;
        }

        return getPuntos() < other.getPuntos()?1:-1;
    }


    public int getPosicion() {
        return posicion;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void sumaPuntosGanados(){
        this.puntos +=3;
    }

    public void sumaPuntosEmpatados(){
        this.puntos +=1;
    }
}
