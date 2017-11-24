package com.upm.entities;

import com.upm.inplementations.PreviewAsHtml;
import com.upm.inplementations.PreviewAsPdf;
import com.upm.inplementations.PreviewAsString;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Clasificacion extends ArrayList<Posicion> {

    private IPreviewData previewData;

    public Clasificacion(List<Equipo> equipos) {
        for (Equipo equipo : equipos) {

            Posicion posicion = new Posicion(equipo) {
                @Override
                public String getPreview(IPreviewData previewData) {
                    return null;
                }
            };

            this.add(posicion);
        }
    }

    public IPreviewData getModePreview() {
        return previewData;
    }

    public String onPreviewData() {
        return previewData.preview(this);
    }

    public void setModePreview(IPreviewData previewData) {
        this.previewData = previewData;
    }

    public void ordenarClasificacion() {

        this.sort(Comparator.comparing(Posicion::getPuntos).reversed());

        for (int i = 0; i < 3; i++) {
            // ascenso
            this.set(i, new PosicionAscenso(this.get(i)));
            // descenso
            this.set(this.size() - i - 1, new PosicionDescenso(this.get(this.size() - i - 1)));
        }

    }

    public void choseModePreview() {
        int option = 0;
        System.out.println("Escoger modo de previzualizacion");
        System.out.println(" 0. Ver por consola");
        System.out.println(" 1. Ver como html");
        System.out.println(" 2. Ver como pdf");

        try{
            InputStreamReader r=new InputStreamReader(System.in);
            BufferedReader br=new BufferedReader(r);
            String selec= br.readLine();
            option = Integer.parseInt(selec);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        switch (option) {
            case 1:
                this.setModePreview(new PreviewAsHtml());
                break;
            case 2:
                this.setModePreview(new PreviewAsPdf());
                break;
            default:
                this.setModePreview(new PreviewAsString());
                break;
        }

    }
}
