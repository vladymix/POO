package com.upm.inplementations;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.upm.entities.Clasificacion;
import com.upm.entities.Equipo;
import com.upm.entities.IPreviewData;
import com.upm.entities.Posicion;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;

public class PreviewAsPdf implements IPreviewData {

    private final String FILE_NAME = "clasificacion.pdf";

    private static BaseColor accentColor = new BaseColor(0, 135, 255, 1);

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, accentColor);

    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);


    @Override
    public String preview(Equipo equipo) {
        return equipo.getName();
    }

    @Override
    public String preview(Posicion posicion) {
        return posicion.getPreview(this);
    }

    @Override
    public String preview(Clasificacion clasificacion) {

        try {

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE_NAME));
            document.open();
            addMetaData(document);
            addTitlePage(document);
            Paragraph subPara = new Paragraph("", subFont);
            addEmptyLine(subPara, 1);

            // add a table
            PdfPTable table = createTable();

            for (Posicion pos : clasificacion) {
                table.addCell(preview(pos.getEquipo()));
                table.addCell(String.valueOf(pos.getGolesFavor()));
                table.addCell(String.valueOf(pos.getGolesContra()));
                table.addCell(String.valueOf(pos.getPuntos()));
            }

            subPara.add(table);
            document.add(subPara);
            document.close();


            Desktop.getDesktop().open(new File(FILE_NAME));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "File pdf Created";
    }


    private void addTitlePage(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Liga de futbol", catFont));
        document.add(preface);
    }

    private void addMetaData(Document document) {
        document.addTitle("LIGA POO");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Fabricio");
        document.addCreator("VLADY");
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private PdfPTable createTable() throws BadElementException {
        PdfPTable table = new PdfPTable(4);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell headers = new PdfPCell();
        headers.setHorizontalAlignment(Element.ALIGN_CENTER);
        headers.setBackgroundColor(accentColor);

        headers.setPhrase(new Phrase("Equipo"));
        table.addCell(headers);
        headers.setPhrase(new Phrase("GF"));
        table.addCell(headers);
        headers.setPhrase(new Phrase("GC"));
        table.addCell(headers);
        headers.setPhrase(new Phrase("Pts"));
        table.addCell(headers);

        table.setHeaderRows(1);

        return table;

    }
}
