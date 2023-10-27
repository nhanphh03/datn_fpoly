package com.example.demo.config;

import com.example.demo.model.HinhAnh;
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

public class ExcelExporterHinhAnh {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<HinhAnh> listHinhAnh;

    public ExcelExporterHinhAnh(List<HinhAnh> listHinhAnh) {
        this.listHinhAnh = listHinhAnh;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Hình Ảnh");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "URL 1", style);
        createCell(row, 2, "URL 2", style);
        createCell(row, 3, "URL 3", style);
        createCell(row, 4, "URL 4", style);
        createCell(row, 5, "Trạng Thái", style);

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

        for (HinhAnh hinhAnh : listHinhAnh) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, String.valueOf(hinhAnh.getIdHinhAnh()), style);
            createCell(row, columnCount++, hinhAnh.getUrl1(), style);
            createCell(row, columnCount++, hinhAnh.getUrl2(), style);
            createCell(row, columnCount++, hinhAnh.getUrl3(), style);
            createCell(row, columnCount++, hinhAnh.getUrl4(), style);
            if (hinhAnh.getTrangThai() == 1) {
                createCell(row, columnCount++, "Hoạt động", style);
            } else if (hinhAnh.getTrangThai() == 0) {
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
