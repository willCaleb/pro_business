package com.will.caleb.business.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;

@Service
public class PdfReportGenerator {

    public <T> byte[] generateReport(List<T> dataList, Class<T> clazz, String tipoRelatorio, String empresaNome, String mesAno) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Document document = new Document(PageSize.A4, 50, 50, 50, 40);
            PdfWriter.getInstance(document, out);
            document.open();

            addHeader(document, empresaNome, tipoRelatorio, mesAno);
            addStyledTable(document, dataList, clazz);

            document.close();
            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
    }

    private void addHeader(Document document, String empresa, String tipoRelatorio, String mesAno) throws DocumentException {
        Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD, Color.BLACK);
        Font labelFont = new Font(Font.HELVETICA, 11, Font.BOLD, Color.DARK_GRAY);
        Font valueFont = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.BLACK);

        // Título centralizado
        Paragraph titulo = new Paragraph("RELATÓRIO: " + tipoRelatorio.toUpperCase(), titleFont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(12f);
        document.add(titulo);

        // Bloco de dados à esquerda
        PdfPTable metaTable = new PdfPTable(2);
        metaTable.setWidthPercentage(60);
        metaTable.setSpacingAfter(10f);
        metaTable.setHorizontalAlignment(Element.ALIGN_LEFT);
        metaTable.setWidths(new float[]{2f, 4f});

        metaTable.addCell(createCell("Empresa:", labelFont));
        metaTable.addCell(createCell(empresa, valueFont));

        metaTable.addCell(createCell("Período:", labelFont));
        metaTable.addCell(createCell(mesAno, valueFont));

        metaTable.addCell(createCell("Emissão:", labelFont));
        metaTable.addCell(createCell(LocalDate.now().toString(), valueFont));

        document.add(metaTable);

        // Linha separadora
        LineSeparator separator = new LineSeparator(1f, 100, Color.LIGHT_GRAY, Element.ALIGN_CENTER, -2);
        document.add(new Chunk(separator));
    }

    private PdfPCell createCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPadding(4f);
        return cell;
    }



    private <T> void addStyledTable(Document document, List<T> dataList, Class<T> clazz) throws DocumentException, IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        PdfPTable table = new PdfPTable(fields.length);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        Font headerFont = new Font(Font.HELVETICA, 10, Font.BOLD, Color.WHITE);
        Font cellFont = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.BLACK);
        Color headerColor = new Color(60, 90, 150); // Azul elegante

        // Cabeçalho
        for (Field field : fields) {
            field.setAccessible(true);
            PdfPCell header = new PdfPCell(new Phrase(formatName(field.getName()), headerFont));
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setVerticalAlignment(Element.ALIGN_MIDDLE);
            header.setBackgroundColor(headerColor);
            header.setPadding(6f);
            header.setBorder(Rectangle.NO_BORDER);
            table.addCell(header);
        }

        // Linhas
        for (T item : dataList) {
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(item);

                PdfPCell cell = new PdfPCell(new Phrase(value != null ? value.toString() : "", cellFont));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setPadding(5f);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setBorderColor(new Color(230, 230, 230)); // cinza claro
                table.addCell(cell);
            }
        }

        document.add(table);
    }

    // Helper para formatar nomes de campos (ex: dataRecebimento → Data Recebimento)
    private String formatName(String raw) {
        return raw.replaceAll("([a-z])([A-Z])", "$1 $2")
                .replace("_", " ")
                .substring(0, 1).toUpperCase() + raw.substring(1).toLowerCase();
    }
}

