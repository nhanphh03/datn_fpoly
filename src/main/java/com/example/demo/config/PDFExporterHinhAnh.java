package com.example.demo.config;

import com.example.demo.model.HinhAnh;
import com.example.demo.model.Size;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;

public class PDFExporterHinhAnh {
    private List<HinhAnh> listHinhAnh;

    public PDFExporterHinhAnh(List<HinhAnh> listHinhAnh) {
        this.listHinhAnh = listHinhAnh;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.PINK);
        cell.setPadding(6);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID HinhAnh", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Image 1", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Image 2", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Image 3", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Image 4", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Trang Thai", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (HinhAnh hinhAnh : listHinhAnh) {
            table.addCell(String.valueOf(hinhAnh.getIdHinhAnh()));
            table.addCell(hinhAnh.getUrl1());
            table.addCell(hinhAnh.getUrl2());
            table.addCell(hinhAnh.getUrl3());
            table.addCell(hinhAnh.getUrl4());
            String trangThaiText = (hinhAnh.getTrangThai() == 1) ? "Hoat Dong" : "Khong Hoat Dong";
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

        Paragraph p = new Paragraph("List of HinhAnh", font);
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
