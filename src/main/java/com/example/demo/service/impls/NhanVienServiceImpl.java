package com.example.demo.service.impls;

import com.example.demo.model.*;
import com.example.demo.repository.ChucVuRepsitory;
import com.example.demo.repository.NhanVienRepsitory;
import com.example.demo.service.NhanVienService;
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
public class NhanVienServiceImpl implements NhanVienService {
    @Autowired
    private NhanVienRepsitory nhanVienRepsitory;
    @Autowired
    private ChucVuRepsitory chucVuRepsitory;

    @Override
    public NhanVien checkByEmailAndPass(String email, String pass) {
        return nhanVienRepsitory.findByEmailNVAndMatKhauAndTrangThai(email, pass, 1);
    }

    @Override
    public NhanVien checkBySDTAndPass(String sdt, String pass) {
        return nhanVienRepsitory.findBySdtNVAndMatKhauAndTrangThai(sdt, pass, 1);
    }

    @Override
    public List<NhanVien> getAllNhanVien() {
        return nhanVienRepsitory.findAll();
    }

    @Override
    public void save(NhanVien nhanVien) {
        nhanVienRepsitory.save(nhanVien);
    }

    @Override
    public void deleteByIdNhanVien(UUID id) {
    nhanVienRepsitory.deleteById(id);
    }

    @Override
    public NhanVien getByIdNhanVien(UUID id) {
        return nhanVienRepsitory.findById(id).orElse(null);
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
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(row.getCell(0).getStringCellValue()); // Cột 0 trong tệp Excel
                nhanVien.setHoTenNV(row.getCell(1).getStringCellValue()); // Cột 1 trong tệp Excel

                // Đối tượng Chức Vụ
                String chucVuName = row.getCell(3).getStringCellValue();
                ChucVu chucVu = chucVuRepsitory.findByTenCV(chucVuName); // Tìm đối tượng ChatLieu theo tên
                nhanVien.setChucVu(chucVu);

//                nhanVien.set(row.getCell(4).getStringCellValue());
                nhanVien.setTrangThai(1);
                nhanVien.setTgThem(new Date());
                nhanVienRepsitory.save(nhanVien);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            // Xử lý lỗi nếu cần
        }
    }

    @Override
    public List<NhanVien> findByChucVu(ChucVu chucVu) {
        return nhanVienRepsitory.findByChucVu(chucVu);
    }

    @Override
    public List<NhanVien> findByTrangThai(int trangThai) {
        return nhanVienRepsitory.findByTrangThai(trangThai);
    }

    @Override
    public List<NhanVien> fillterNhanVien(String maNV, String tenNV) {
        if ("Mã Nhân Viên".equals(maNV) && "Tên Nhân Viên".equals(tenNV)) {
            return nhanVienRepsitory.findAll();
        }
        return nhanVienRepsitory.findByMaNVOrHoTenNV(maNV, tenNV);
    }
}
