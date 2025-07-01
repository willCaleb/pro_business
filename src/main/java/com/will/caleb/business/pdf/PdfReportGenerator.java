package com.will.caleb.business.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;

@Service
public class PdfReportGenerator {

    public <T> byte[] generateReport(List<T> dataList, Class<T> clazz, String tipoRelatorio, String empresaNome, String mesAno) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, out);
            document.open();

            // Título e cabeçalho
            addHeader(document, empresaNome, tipoRelatorio, mesAno);

            // Tabela de dados
            addTable(document, dataList, clazz);

            document.close();
            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
    }

    private void addHeader(Document document, String empresa, String tipoRelatorio, String mesAno) throws DocumentException {
        Font titleFont = new Font(Font.HELVETICA, 16, Font.BOLD);
        Font metaFont = new Font(Font.HELVETICA, 12, Font.NORMAL);

        Paragraph title = new Paragraph("Relatório: " + tipoRelatorio, titleFont);
        title.setAlignment(Element.ALIGN_CENTER);

        Paragraph empresaNome = new Paragraph("Empresa: " + empresa, metaFont);
        Paragraph periodo = new Paragraph("Período: " + mesAno, metaFont);
        Paragraph data = new Paragraph("Data de emissão: " + LocalDate.now(), metaFont);

        empresaNome.setSpacingBefore(15);
        data.setSpacingAfter(20);

        document.add(title);
        document.add(empresaNome);
        document.add(periodo);
        document.add(data);
    }

    private <T> void addTable(Document document, List<T> dataList, Class<T> clazz) throws DocumentException, IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        PdfPTable table = new PdfPTable(fields.length);
        table.setWidthPercentage(100);

        Font headerFont = new Font(Font.HELVETICA, 10, Font.BOLD);
        Font bodyFont = new Font(Font.HELVETICA, 10);

        // Cabeçalhos da tabela
        for (Field field : fields) {
            field.setAccessible(true);
            table.addCell(new Phrase(field.getName(), headerFont));
        }

        // Dados
        for (T item : dataList) {
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(item);
                table.addCell(new Phrase(value != null ? value.toString() : "", bodyFont));
            }
        }

        document.add(table);
    }
}
