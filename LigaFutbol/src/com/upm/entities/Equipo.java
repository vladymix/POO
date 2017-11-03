package com.upm.entities;

import java.util.ArrayList;
import java.util.List;

public class Equipo extends Clasificacion {

    private String name;

    private int golesFavor =0;
    private int golesContra =0;

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

    public int getGolesContra() {
        return golesContra;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public String getName() {
        return name;
    }

    public void setGolesContra(int golesContra) {
        this.golesContra = golesContra;
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

        // Empate
        if (partidoJugado.getGolesLocal() == partidoJugado.getGolesVisitantes()) {
            this.partidosEmpatados.add(partidoJugado);
            this.sumaGoles(partidoJugado.getGolesLocal(),partidoJugado.getGolesVisitantes());
        } else {

            if (jugoComoLocal) {
                this.sumaGoles(partidoJugado.getGolesLocal(),partidoJugado.getGolesVisitantes());

                if (partidoJugado.getGolesLocal() > partidoJugado.getGolesVisitantes()) {
                    this.partidosGanados.add(partidoJugado);
                } else {
                    this.partidosPerdidos.add(partidoJugado);
                }
            } else {

                this.sumaGoles(partidoJugado.getGolesVisitantes(),partidoJugado.getGolesLocal());

                if (partidoJugado.getGolesLocal() < partidoJugado.getGolesVisitantes()) {
                    this.partidosGanados.add(partidoJugado);
                } else {
                    this.partidosPerdidos.add(partidoJugado);
                }
            }
        }
    }

    private void sumaGoles(int favor, int contra){
        this.golesFavor +=favor;
        this.golesContra +=contra;

        if(favor> contra){
            this.sumaPuntosGanados();
        }
        else if(favor ==contra){
           this.sumaPuntosEmpatados();
        }
    }
}
