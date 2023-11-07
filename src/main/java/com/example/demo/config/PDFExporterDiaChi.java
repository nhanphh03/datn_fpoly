package com.example.demo.config;

import com.example.demo.model.DiaChiKH;
import com.example.demo.model.Giay;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PDFExporterDiaChi {
    private List<DiaChiKH> listDiaChi;

    public PDFExporterDiaChi(List<DiaChiKH> listDiaChi) {
        this.listDiaChi = listDiaChi;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.PINK);
        cell.setPadding(6);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID Dia chi", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Ma Dia Chi", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Ten Dia Chi", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Khach Hang", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Trang Thai", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (DiaChiKH diaChiKH : listDiaChi) {
            table.addCell(String.valueOf(diaChiKH.getIdDC()));
            table.addCell(diaChiKH.getMaDC());
            table.addCell(diaChiKH.getTenDC());
            table.addCell(diaChiKH.getKhachHang().getHoTenKH());
            String trangThaiText = (diaChiKH.getTrangThai() == 1) ? "Hoat Dong" : "Khong Hoat Dong";
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

        Paragraph p = new Paragraph("List of DiaChi", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{3.5f, 2.0f, 2.0f, 2.5f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
