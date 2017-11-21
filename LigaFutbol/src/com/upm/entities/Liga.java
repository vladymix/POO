package com.upm.entities;

import com.upm.inplementations.PreviewAsString;
import com.upm.utilities.Utils;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Liga {

    private List<Equipo> equipos = new ArrayList<>();
    private Clasificacion clasificacion;

    private ArrayList<Partido> partidos= new ArrayList<>();


    public Liga(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public Liga() {
        this.crearEquipos();
        clasificacion = new Clasificacion(equipos);
        this.clasificacion.setModePreview(new PreviewAsString());
    }

    public void crearEquipos(){
        this.equipos = Utils.getEquipos();
    }

    public void generarPartidos(){
        // Generar todos los posibles partidos
        // realizando todas las posibles combinaciones
        // de los equipos, incluyendo ida y vuelta
        this.setPartidos(Utils.getPartidos(this.getEquipos()));

        // asignando un resultado al azar

        int i =1;
        for(Partido partido : this.getPartidos()){
            if(partido!=null){

                // asignando un resultado al azar
                partido.setResultado(Utils.getGolesRandom(), Utils.getGolesRandom());

                String description = String.format("Partido nº%d  %s  vs %s | %d - %d", i, partido.getEquipoLocal().getName(), partido.getEquipoVisitante().getName(), partido.getGolesLocal(), partido.getGolesVisitantes());

                System.out.println(description);

                for(Equipo equipo : this.getEquipos()){
                    // Si en el partido juega como visitante o local añadir a la lista de partidos jugados
                    if(equipo.getName().equals(partido.getEquipoLocal().getName()) || equipo.getName().equals(partido.getEquipoVisitante().getName())){
                        equipo.addPartido(partido);
                    }
                }
                i++;
            }
        }

    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }

    public void showHtml(){
        try{
             File file = new File("index.html");
             Desktop.getDesktop().open(file);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void start(){

        this.generarPartidos();

        // To string
        this.visualizar();

        // With interfaces
        this.verClasificacion();

    }

    public void visualizar(){

        for (Equipo equipo : this.getEquipos()) {
            // If reload statics equipo.resetAndLoadStatistics();
            // visualizar nombre el equipo
            visualizarEquipo(equipo);

            // visualizar todos los partidos del equipo
            visualizarPartidosEquipo(equipo);

            // visualizar partidos ganados
            visualizarEmpatados(equipo);

            // visualizar partidos perdidos
            visualizarPerdidos(equipo);

            // visualizar partidos empatados
            visualizarEmpatados(equipo);
        }
    }

    public void visualizarEmpatados(Equipo equipo){
        // visualizar partidos empatados
        System.out.println(String.format("Empatados: %s", equipo.getPartidosEmpatadosAsString()));
    }

    public void visualizarEquipo(Equipo equipo){
        System.out.println(equipo.getName());
    }

    public void visualizarGanados(Equipo equipo){
        // visualizar partidos ganados
        System.out.println(String.format("GANADOS: %s", equipo.getPartidosGanadosAsString()));
    }

    public void visualizarPartidosEquipo(Equipo equipo){
        // visualizar todos los partidos del equipo
        System.out.println(String.format("TODOS: %s", equipo.getTodosPartidosAsString()));

    }

    public void visualizarPerdidos(Equipo equipo){
        // visualizar partidos perdidos
        System.out.println(String.format("PERDIDOS: %s", equipo.getPartidosPerdidosAsString()));
    }

    private void verClasificacion() {
        this.clasificacion.ordenarClasificacion();
        System.out.println("Output preview Data");
        System.out.println(this.clasificacion.onPreviewData());

    }
}
