package com.upm.entities;

import java.util.ArrayList;
import java.util.List;

public class Equipo {

    private String name;

    private List<Partido> partidos = new ArrayList<>();
    private List<Partido> partidosGanados = new ArrayList<>();
    private List<Partido> partidosPerdidos= new ArrayList<>();
    private List<Partido> partidosEmpatados= new ArrayList<>();

    public Equipo(String name) {
        this.name = name;
    }

    public void addPartido(Partido partidoJugado) {
        this.loadStatistics(partidoJugado);
        partidos.add(partidoJugado);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public String getPartidosEmpatadosAsString(){
        return this.getPartidosAsString(this.partidosEmpatados);
    }

    public String getPartidosGanadosAsString(){
        return this.getPartidosAsString(this.partidosGanados);
    }

    public String getPartidosPerdidosAsString(){
        return this.getPartidosAsString(this.partidosPerdidos);
    }

    public String getTodosPartidosAsString(){
        return this.getPartidosAsString(this.partidos);
    }

    public void resetAndLoadStatistics() {

        this.partidosPerdidos = new ArrayList<>();
        this.partidosGanados = new ArrayList<>();
        this.partidosEmpatados = new ArrayList<>();

        for (Partido partido : this.partidos) {

            boolean jugoComoLocal = partido.getEquipoLocal().getName().equals(this.getName());

            if (partido.getGolesLocal() == partido.getGolesVisitantes()) {
                this.partidosEmpatados.add(partido);
            } else {
                if (jugoComoLocal) {
                    if (partido.getGolesLocal() > partido.getGolesVisitantes()) {
                        this.partidosGanados.add(partido);
                    } else {
                        this.partidosPerdidos.add(partido);
                    }
                } else {
                    if (partido.getGolesLocal() < partido.getGolesVisitantes()) {
                        this.partidosGanados.add(partido);
                    } else {
                        this.partidosPerdidos.add(partido);
                    }
                }
            }
        }
    }

    private String getPartidosAsString(List<Partido> partidos){
        String partidosString = "";

        for(int i =0; i < partidos.size();i++){
            if(i < partidos.size()-1){
                partidosString += partidos.get(i).toString()+", ";
            }else{
                partidosString += partidos.get(i).toString();
            }
        }
        return String.format("[%s]",partidosString );
    }

    private void loadStatistics(Partido partidoJugado) {

        boolean jugoComoLocal = partidoJugado.getEquipoLocal().getName().equals(this.getName());

        if (partidoJugado.getGolesLocal() == partidoJugado.getGolesVisitantes()) {
            this.partidosEmpatados.add(partidoJugado);
        } else {
            if (jugoComoLocal) {
                if (partidoJugado.getGolesLocal() > partidoJugado.getGolesVisitantes()) {
                    this.partidosGanados.add(partidoJugado);
                } else {
                    this.partidosPerdidos.add(partidoJugado);
                }
            } else {
                if (partidoJugado.getGolesLocal() < partidoJugado.getGolesVisitantes()) {
                    this.partidosGanados.add(partidoJugado);
                } else {
                    this.partidosPerdidos.add(partidoJugado);
                }
            }
        }
    }
}
