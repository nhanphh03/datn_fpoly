package com.example.demo.config;

import com.example.demo.model.MauSac;
import com.example.demo.model.Size;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PDFExporterMauSac {
    private List<MauSac> listMauSac;

    public PDFExporterMauSac(List<MauSac> listMauSac) {
        this.listMauSac = listMauSac;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.PINK);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID Mau", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Ma Mau", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Ten Mau", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Trang Thai", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (MauSac mauSac : listMauSac) {
            table.addCell(String.valueOf(mauSac.getIdMau()));
            table.addCell(mauSac.getMaMau());
            table.addCell(mauSac.getTenMau());
            String trangThaiText = (mauSac.getTrangThai() == 1) ? "Hoat Dong" : "Khong Hoat Dong";
            table.addCell(trangThaiText);
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);

        Paragraph p = new Paragraph("List of MauSac", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{3.5f, 1.5f, 1.5f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
