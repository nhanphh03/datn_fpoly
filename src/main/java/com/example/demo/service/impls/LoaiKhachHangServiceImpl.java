package com.example.demo.service.impls;

import com.example.demo.model.LoaiKhachHang;
import com.example.demo.repository.LoaiKhachHangRepository;
import com.example.demo.service.LoaiKhachHangService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class LoaiKhachHangServiceImpl implements LoaiKhachHangService {
    @Autowired
    public LoaiKhachHangRepository loaiKhachHangRepository;

    @Override
    public List<LoaiKhachHang> getAllLoaiKhachHang() {
        return loaiKhachHangRepository.findAll();
    }

    @Override
    public void save(LoaiKhachHang loaiKhachHang) {
        loaiKhachHangRepository.save(loaiKhachHang);
    }

    @Override
    public void deleteByIdLoaiKhachHang(UUID id) {
        loaiKhachHangRepository.deleteById(id);
    }

    @Override
    public LoaiKhachHang getByIdLoaiKhachHang(UUID id) {
        return loaiKhachHangRepository.findById(id).orElse(null);
    }

    @Override
    public List<LoaiKhachHang> getAllActive() {
        return loaiKhachHangRepository.getByTrangThai(1);
    }

    @Override
    public List<LoaiKhachHang> fillterLKH(String maLKH, String tenLKH) {
        if ("Mã Loại Khách Hàng".equals(maLKH) && "Tên Loại Khách Hàng".equals(tenLKH)) {
            return loaiKhachHangRepository.findAll();
        }
        return loaiKhachHangRepository.findByMaLKHOrTenLKH(maLKH, tenLKH);
    }

    @Override
    public LoaiKhachHang findByMaLKH(String maLKH) {
        return loaiKhachHangRepository.findByMaLKH(maLKH);
    }

    @Override
    public void importDataFromExcel(InputStream excelFile) {
        try (Workbook workbook = new XSSFWorkbook(excelFile)) {
            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên (index 0)

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    // Bỏ qua hàng đầu tiên nếu nó là tiêu đề
                    continue;
                }
                LoaiKhachHang loaiKhachHang = new LoaiKhachHang();
                loaiKhachHang.setMaLKH(row.getCell(0).getStringCellValue()); // Cột 0 trong tệp Excel
                loaiKhachHang.setTenLKH(row.getCell(1).getStringCellValue()); // Cột 0 trong tệp Excel
                loaiKhachHang.setTgThem(new Date());
                loaiKhachHang.setTrangThai(1);
                loaiKhachHangRepository.save(loaiKhachHang);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần

        }

    }
}
