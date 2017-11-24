package com.upm.inplementations;

import com.upm.entities.Clasificacion;
import com.upm.entities.Equipo;
import com.upm.entities.IPreviewData;
import com.upm.entities.Posicion;

import javax.swing.text.Position;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class PreviewAsHtml implements IPreviewData {

    private final String FILE_NAME = "index.html";

    @Override
    public String preview(Equipo equipo) {
        return "\n\t<td>"+ equipo.getName()+"</td>";
    }

    @Override
    public String preview(Posicion posicion) {
        return "<tr>" +
                    preview(posicion.getEquipo())+
                    "\n\t<td style='text-align: center'>"+posicion.getGolesFavor()+"</td>" +
                    "\n\t<td style='text-align: center'>"+posicion.getGolesContra()+"</td>" +
                    "\n\t<td style='text-align: center'>"+posicion.getPuntos()+"</td>" +
                "\n</tr>\n";
    }

    @Override
    public String preview(Clasificacion clasificacion) {
        String itemsPosition ="";
        for(Posicion posicion:clasificacion){
            itemsPosition +=preview(posicion);
        }

        String htlm = "<html>\n" +
                "<body>\n" +
                "<h1 style=\"color: #0087ff;\">Liga de futbol</h1>\n" +
                "<table style=\"width: 538px;\">\n" +
                "\n" +
                "<thead style=\"\n" +
                "       background-color: #0087ff;\n" +
                "    \tcolor: white;\n" +
                "    \ttext-align: center;\n" +
                "    \tpadding: 3px 10px;\n" +
                "    \tborder: 1px solid #999999;\">\n" +
                "<tr>\n" +
                "<td style=\"width: 172px;\">Equipo</td>\n" +
                "<td style=\"width: 138px;\">GF</td>\n" +
                "<td style=\"width: 204px;\">GC</td>\n" +
                "<td style=\"width: 204px;\">Pts</td>\n" +
                "</tr>\n" +
                "</thead>\n" +
                "\n" +
                "<tbody>\n" +itemsPosition+"</tbody>\n" +
                "</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>";


        try{
            File file = new File(FILE_NAME);
            if(file.exists()){
                file.delete();
            }

            BufferedWriter writer = null;
            writer = new BufferedWriter( new FileWriter( FILE_NAME));
            writer.write( htlm);

            if ( writer != null)
                writer.close( );

            file = new File(FILE_NAME);

            Desktop.getDesktop().open(file);
        }catch (Exception ex){
            ex.printStackTrace();
        }


        return "File Html Created";
    }
}
