package com.upm;

import com.upm.entities.Equipo;
import com.upm.entities.Partido;
import com.upm.utilities.Utils;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private List<Equipo> equipos = new ArrayList<>();
    private ArrayList<Partido> partidos= new ArrayList<>();

    public static void main(String[] args) {
        // write your code here
        new Main().start();
    }

    private void start() {
        // crear todos los equipos
        this.equipos = Utils.getEquipos();

        // Generar todos los posibles partidos
        // realizando todas las posibles combinaciones
        // de los equipos, incluyendo ida y vuelta
        this.partidos = Utils.getPartidos(this.equipos);
        int i =1;
        for(Partido partido : this.partidos){

            if(partido!=null){
                // asignando un resultado al azar
                partido.setResultado(Utils.getGolesRandom(), Utils.getGolesRandom());

                String description = String.format("Partido nº%d  %s  vs %s | %d - %d", i, partido.getEquipoLocal().getName(), partido.getEquipoVisitante().getName(), partido.getGolesLocal(), partido.getGolesVisitantes());

                System.out.println(description);

                for(Equipo equipo : this.equipos){
                    // Si en el partido juega como visitante o local añadir a la lista de partidos jugados
                    if(equipo.getName().equals(partido.getEquipoLocal().getName()) || equipo.getName().equals(partido.getEquipoVisitante().getName())){
                        equipo.addPartido(partido);
                    }
                }
                i++;
            }
        }

        for (Equipo equipo : equipos) {
            // If reload statics equipo.resetAndLoadStatistics();
            // visualizar nombre el equipo
            System.out.println(equipo.getName());
            // visualizar todos los partidos del equipo
            System.out.println(String.format("TODOS: %s", equipo.getTodosPartidosAsString()));
            // visualizar partidos ganados
            System.out.println(String.format("GANADOS: %s", equipo.getPartidosGanadosAsString()));
            // visualizar partidos perdidos
            System.out.println(String.format("PERDIDOS: %s", equipo.getPartidosPerdidosAsString()));
            // visualizar partidos empatados
            System.out.println(String.format("Empatados: %s", equipo.getPartidosEmpatadosAsString()));
        }
    }
}
