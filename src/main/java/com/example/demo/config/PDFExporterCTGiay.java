package com.example.demo.config;

import com.example.demo.model.ChiTietGiay;
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

public class PDFExporterCTGiay {
    private List<ChiTietGiay> listCTGiay;

    public PDFExporterCTGiay(List<ChiTietGiay> listCTGiay) {
        this.listCTGiay = listCTGiay;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.PINK);
        cell.setPadding(11);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Hinh Anh", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Ten", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Size", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Mau Sac", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("So Luong", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Gia Ban", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Trong Luong", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Nam San Xuat", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Nam Bao Hanh", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Trang Thai", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (ChiTietGiay chiTietGiay : listCTGiay) {
            table.addCell(String.valueOf(chiTietGiay.getIdCTG()));
            table.addCell(chiTietGiay.getHinhAnh().getUrl1());
            table.addCell(chiTietGiay.getGiay().getTenGiay());
            table.addCell(String.valueOf(chiTietGiay.getSize().getSoSize()));
            table.addCell(chiTietGiay.getMauSac().getTenMau());
            table.addCell(String.valueOf(chiTietGiay.getSoLuong()));
            table.addCell(String.valueOf(chiTietGiay.getGiaBan()));
            table.addCell(String.valueOf(chiTietGiay.getTrongLuong()));
            table.addCell(String.valueOf(chiTietGiay.getNamSX()));
            table.addCell(String.valueOf(chiTietGiay.getNamBH()));
            String trangThaiText = (chiTietGiay.getTrangThai() == 1) ? "Hoat Dong" : "Khong Hoat Dong";
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

        Paragraph p = new Paragraph("List of CTGiays", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(11);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{3.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
