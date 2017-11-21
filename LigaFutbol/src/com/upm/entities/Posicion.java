package com.upm.entities;

public abstract class Posicion {

    private Equipo equipo;
    private int golesFavor = 0;
    private int golesContra = 0;
    private int puntos = 0;
    private int partidos_ganados = 0;
    private int partidos_empatados = 0;
    private int partidos_perdidos = 0;

    public abstract String getPreview(IPreviewData previewData);

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

    public Equipo getEquipo() {
        return equipo;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public int getPartidos_empatados() {
        return partidos_empatados;
    }

    public int getPartidos_ganados() {
        return partidos_ganados;
    }

    public int getPartidos_perdidos() {
        return partidos_perdidos;
    }

    public int getPuntos() {
        return puntos;
    }

    public String toString(){
        return this.getEquipo().getName() + " pts "+ this.getPuntos();
    }
}
