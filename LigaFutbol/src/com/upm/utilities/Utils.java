package com.upm.utilities;

import com.upm.entities.Equipo;
import com.upm.entities.Partido;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    public static String nombres[] = { "Real Madrid",
            "Barcelona",
            "Atletico de Madrid",
            "Alaves",
            "Sevilla",
            "Real Sociedad" };

    /**
     * @return total equipos
     */
    public static ArrayList<Equipo> getEquipos(){
        ArrayList<Equipo> equipos = new ArrayList<>();
        for(String name: nombres){
            equipos.add(new Equipo(name));
        }
        return equipos;

    }

    /**
     * Variaciones de m elementos tomados de 2 en 2. (ida y vuelta) con 6 equipos = 30 partidos
     * @param equipos
     * @return
     */
    public static ArrayList<Partido> getPartidos(List<Equipo> equipos){

        ArrayList<Partido> partidos = new ArrayList<>();
        for(int i =0; i< equipos.size(); i++){
            // Partidos ida
            for(int j = i; j< equipos.size();j++){

                // No insert for example Real Madrid - Real Madrid
                if(!equipos.get(i).getName().equals(equipos.get(j).getName())){
                    // Set ida
                    partidos.add(new Partido(equipos.get(i), equipos.get(j)));
                    // Set vuelta
                    partidos.add(new Partido(equipos.get(j), equipos.get(i)));
                }
            }

        }
        return partidos;
    }

    /**
     * @return devuelve un número de goles al azar entre 0 y 7
     */
    public static int getGolesRandom(){

        int min = 0;
        int max = 7;
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
