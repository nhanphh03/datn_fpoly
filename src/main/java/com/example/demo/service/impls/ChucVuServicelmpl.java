package com.example.demo.service.impls;

import com.example.demo.model.ChatLieu;
import com.example.demo.model.ChucVu;
import com.example.demo.repository.ChucVuRepsitory;
import com.example.demo.service.ChucVuService;
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
public class ChucVuServicelmpl implements ChucVuService {

    @Autowired
    private ChucVuRepsitory chucVuRepsitory;

    @Override
    public List<ChucVu> getAllChucVu() {
        return chucVuRepsitory.findAll();
    }

    @Override
    public void save(ChucVu chucVu) {
        chucVuRepsitory.save(chucVu);
    }

    @Override
    public void deleteByIdChucVu(UUID id) {
        chucVuRepsitory.deleteById(id);
    }

    @Override
    public ChucVu getByIdChucVu(UUID id) {
        return chucVuRepsitory.findById(id).orElse(null);
    }

    @Override
    public List<ChucVu> getAllActive() {
        return chucVuRepsitory.getByTrangThai(1);
    }

    @Override
    public List<ChucVu> fillterChucVu(String maCV, String tenCV) {
        if ("Mã Chức Vụ".equals(maCV) && "Tên Chức Vụ".equals(tenCV)) {
            return chucVuRepsitory.findAll();
        }
        return chucVuRepsitory.findByMaCVOrTenCV(maCV, tenCV);

    }

    @Override
    public ChucVu findByMaCV(String maCV) {
        return chucVuRepsitory.findByMaCV(maCV);
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
                ChucVu chucVu = new ChucVu();
                chucVu.setMaCV(row.getCell(0).getStringCellValue()); // Cột 0 trong tệp Excel
                chucVu.setTenCV(row.getCell(1).getStringCellValue()); // Cột 0 trong tệp Excel
                chucVu.setTgThem(new Date());
                chucVu.setTrangThai(1);
                chucVuRepsitory.save(chucVu);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }
    }
}
