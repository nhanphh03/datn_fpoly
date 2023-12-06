package com.example.demo.config;


import com.example.demo.model.LoaiKhachHang;
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

public class ExcelExporterLoaiKhachHang {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<LoaiKhachHang> listLoaiKhachHang;

    public ExcelExporterLoaiKhachHang(List<LoaiKhachHang> listLoaiKhachHang) {
        this.listLoaiKhachHang = listLoaiKhachHang;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Loại Khách Hàng");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Mã", style);
        createCell(row, 2, "Tên Loại Khách Hàng", style);
        createCell(row, 3, "Số Điểm", style);
        createCell(row, 4, "Trạng Thái", style);

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

        for (LoaiKhachHang loaiKhachHang : listLoaiKhachHang) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, String.valueOf(loaiKhachHang.getIdLKH()), style);
            createCell(row, columnCount++, loaiKhachHang.getMaLKH(), style);
            createCell(row, columnCount++, loaiKhachHang.getTenLKH(), style);
            createCell(row, columnCount++, loaiKhachHang.getSoDiem(), style);
            if (loaiKhachHang.getTrangThai() == 1) {
                createCell(row, columnCount++, "Hoạt động", style);
            } else if (loaiKhachHang.getTrangThai() == 0) {
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
