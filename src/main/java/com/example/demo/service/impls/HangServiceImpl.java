package com.example.demo.service.impls;

import com.example.demo.model.Hang;
import com.example.demo.model.Size;
import com.example.demo.repository.HangRepository;
import com.example.demo.service.HangService;
import org.apache.poi.ss.usermodel.CellType;
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
public class HangServiceImpl implements HangService {
    @Autowired
    private HangRepository hangRepository;

    @Override
    public List<Hang> getALlHang() {
        return hangRepository.findAllByOrderByTgThemDesc();
    }

    @Override
    public void save(Hang hang) {
        hangRepository.save(hang);
    }

    @Override
    public void deleteByIdHang(UUID id) {
        hangRepository.deleteById(id);
    }

    @Override
    public Hang getByIdHang(UUID id) {
        return hangRepository.findById(id).orElse(null);
    }

    @Override
    public List<Hang> getAllActive() {

        return hangRepository.getByTrangThai(1);
    }

    @Override
    public List<Hang> fillterHang(String maHang, String tenHang) {
        if ("Mã Hãng".equals(maHang) && "Tên Hãng".equals(tenHang)) {
            return hangRepository.findAll();
        }
        return hangRepository.findByMaHangOrTenHang(maHang, tenHang);
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
                Hang hang = new Hang();
                hang.setMaHang(row.getCell(0).getStringCellValue()); // Cột 0 trong tệp Excel
                hang.setTenHang(row.getCell(1).getStringCellValue()); // Cột 0 trong tệp Excel
                hang.setTgThem(new Date());
                hang.setTrangThai(1);
                hangRepository.save(hang);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }
    }
}
