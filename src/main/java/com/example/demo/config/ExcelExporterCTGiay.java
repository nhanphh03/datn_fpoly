package com.example.demo.config;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.Size;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

public class ExcelExporterCTGiay {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<ChiTietGiay> listCTGiay;

    public ExcelExporterCTGiay(List<ChiTietGiay> listCTGiay) {
        this.listCTGiay = listCTGiay;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Chi Tiết Giày");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Hình Ảnh", style);
        createCell(row, 2, "Tên", style);
        createCell(row, 3, "Size", style);
        createCell(row, 4, "Màu Sắc", style);
        createCell(row, 5, "Số Lượng", style);
        createCell(row, 6, "Giá Bán", style);
        createCell(row, 7, "Trọng Lượng", style);
        createCell(row, 8, "Năm Sản Xuất", style);
        createCell(row, 9, "Năm Bảo Hành", style);
        createCell(row, 10, "Trạng Thái", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (ChiTietGiay chiTietGiay : listCTGiay) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, String.valueOf(chiTietGiay.getIdCTG()), style);
            createCell(row, columnCount++, chiTietGiay.getHinhAnh().getUrl1(), style);
            createCell(row, columnCount++, chiTietGiay.getGiay().getTenGiay(), style);
            createCell(row, columnCount++, chiTietGiay.getSize().getSoSize(), style);
            createCell(row, columnCount++, chiTietGiay.getMauSac().getTenMau(), style);
            createCell(row, columnCount++, chiTietGiay.getSoLuong(), style);
            createCell(row, columnCount++, String.valueOf(chiTietGiay.getGiaBan()), style);
            createCell(row, columnCount++, chiTietGiay.getTrongLuong(), style);
            createCell(row, columnCount++, chiTietGiay.getNamSX(), style);
            createCell(row, columnCount++, chiTietGiay.getNamBH(), style);
            if (chiTietGiay.getTrangThai() == 1) {
                createCell(row, columnCount++, "Hoạt động", style);
            } else if (chiTietGiay.getTrangThai() == 0) {
                createCell(row, columnCount++, "Không hoạt động", style);
            } else {
                // Nếu giá trị trạng thái không phải là 0 hoặc 1, bạn có thể xử lý tùy ý ở đây.
                createCell(row, columnCount++, "Giá trị không hợp lệ", style);
            }

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
