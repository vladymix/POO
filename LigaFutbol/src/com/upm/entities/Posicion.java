package com.upm.entities;

public class Posicion {

    private Equipo equipo;

    private int golesFavor = 0;
    private int golesContra = 0;
    private int puntos = 0;
    private int partidos_ganados = 0;
    private int partidos_empatados = 0;
    private int partidos_perdidos = 0;

    private int position;

    public Posicion(Equipo equipo) {
        this.equipo = equipo;
        this.equipo.setPosicion(this);
    }

    // Contructor para copia
    // Se ha vuelto ha establecer la relación de asociación Posicion con Equipo para no perder
    // las referencias.
    public Posicion(Posicion posicion) {
        this.equipo = posicion.equipo;
        this.equipo.setPosicion(this);
        this.golesFavor = posicion.getGolesFavor();
        this.golesContra = posicion.getGolesContra();
        this.partidos_perdidos = posicion.getPartidos_perdidos();
        this.partidos_ganados = posicion.getPartidos_ganados();
        this.partidos_empatados = posicion.getPartidos_empatados();
        this.puntos = posicion.getPuntos();
    }


    public void actualizaPosicion(Equipo equipo, Partido partido) {
        if (partido.haGanado(equipo)) {
            puntos += 3;
            partidos_ganados++;
        } else if (partido.haEmpatado()) {
            puntos++;
            partidos_empatados++;
        } else partidos_perdidos++;
        golesFavor += partido.golesFavor(equipo);
        golesContra += partido.golesContra(equipo);

    }

    public int getGolesContra() {
        return golesContra;
    }

    public void setGolesContra(int golesContra) {
        this.golesContra = golesContra;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public void setGolesFavor(int golesFavor) {
        this.golesFavor = golesFavor;
    }

    public int getPartidos_empatados() {
        return partidos_empatados;
    }

    public void setPartidos_empatados(int partidos_empatados) {
        this.partidos_empatados = partidos_empatados;
    }

    public int getPartidos_ganados() {
        return partidos_ganados;
    }

    public void setPartidos_ganados(int partidos_ganados) {
        this.partidos_ganados = partidos_ganados;
    }

    public int getPartidos_perdidos() {
        return partidos_perdidos;
    }

    public void setPartidos_perdidos(int partidos_perdidos) {
        this.partidos_perdidos = partidos_perdidos;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String toString(){
        return equipo.getName() + " pts "+ puntos + " PG "+ partidos_ganados+" PE " + partidos_empatados + " PP "+partidos_perdidos;
    }
}
