package com.example.demo.config;

import com.example.demo.model.Giay;
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

public class PDFExporterGiays {
    private List<Giay> listGiay;

    public PDFExporterGiays(List<Giay> listGiay) {
        this.listGiay = listGiay;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.PINK);
        cell.setPadding(6);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID Giay", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Ma Giay", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Ten Giay", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Hang", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Chat Lieu", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Trang Thai", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (Giay giay : listGiay) {
            table.addCell(String.valueOf(giay.getIdGiay()));
            table.addCell(giay.getMaGiay());
            table.addCell(giay.getTenGiay());
            table.addCell(giay.getHang().getTenHang());
            table.addCell(giay.getChatLieu().getTenChatLieu());
            String trangThaiText = (giay.getTrangThai() == 1) ? "Hoat Dong" : "Khong Hoat Dong";
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

        Paragraph p = new Paragraph("List of Giays", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{3.5f, 1.5f, 1.5f, 1.5f, 1.5f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
