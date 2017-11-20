package com.upm.entities;

import java.util.ArrayList;
import java.util.List;

public class Equipo {

    private String name;

    private Posicion posicion;

    private List<Partido> partidos = new ArrayList<>();
    private List<Partido> partidosGanados = new ArrayList<>();
    private List<Partido> partidosPerdidos= new ArrayList<>();
    private List<Partido> partidosEmpatados= new ArrayList<>();

    public Equipo(String name) {
        this.name = name;
    }

    public void actualizaClasificacion(Partido partido) {
        this.posicion.actualizaPosicion(this, partido);
        this.partidos.add(partido);
        this.savePartido(partido);
    }

    private void savePartido(Partido partidoJugado) {

        boolean jugoComoLocal = partidoJugado.getEquipoLocal().getName().equals(this.getName());

        // Empate
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

    public void addPartido(Partido partidoJugado) {
        this.actualizaClasificacion(partidoJugado);
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

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
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
}
