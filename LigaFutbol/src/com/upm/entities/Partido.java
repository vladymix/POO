package com.upm.entities;

public class Partido {

    private final Equipo visitante;
    private final Equipo local;

    private int golesLocal;
    private int golesVisitante;

    public Partido(Equipo local, Equipo visitante) {
        this.local = local;
        this.visitante = visitante;
    }

    public Equipo getEquipoLocal() {
        return this.local;
    }

    public Equipo getEquipoVisitante() {
        return this.visitante;
    }

    public Equipo getGanador() {
        return this.getGolesLocal() > this.getGolesVisitantes() ? this.local : this.visitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public int getGolesVisitantes() {
        return golesVisitante;
    }

    public int golesContra(Equipo equipo) {
        boolean jugoComoLocal = this.getEquipoLocal().getName().equals(equipo.getName());
        if (jugoComoLocal) {
            return this.getGolesVisitantes();
        } else {
            return this.getGolesLocal();
        }
    }

    public int golesFavor(Equipo equipo) {
        boolean jugoComoLocal = this.getEquipoLocal().getName().equals(equipo.getName());
        if (jugoComoLocal) {
            return this.getGolesLocal();
        } else {
            return this.getGolesVisitantes();
        }
    }

    public boolean haEmpatado() {
        return golesLocal == golesVisitante;
    }

    public boolean haGanado(Equipo equipo) {
        return this.getGanador().getName().equals(equipo.getName());
    }

    public void setResultado(int golesLocal, int golesVisitante) {
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.local.actualizaClasificacion(this);
        this.visitante.actualizaClasificacion(this);
    }

    @Override
    public String toString() {
        return String.format("%s (%d) - %s (%d)", this.local.getName(), this.getGolesLocal(), this.visitante.getName(), this.getGolesVisitantes());
    }
}
