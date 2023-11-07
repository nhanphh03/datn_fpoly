package com.example.demo.service.impls;


import com.example.demo.model.KhachHang;
import com.example.demo.model.LoaiKhachHang;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.repository.LoaiKhachHangRepository;
import com.example.demo.service.KhachHangService;
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
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private LoaiKhachHangRepository loaiKhachHangRepository;

    @Override
    public KhachHang checkLoginSDT(String sdt, String pass) {
        return khachHangRepository.findBySdtKHAndTrangThaiAndMatKhau(sdt, 1, pass);
    }

    @Override
    public KhachHang checkLoginEmail(String email, String pass) {
        return khachHangRepository.findByEmailKHAndTrangThaiAndMatKhau(email, 1, pass);
    }

    @Override
    public KhachHang checkEmail(String email) {
        return khachHangRepository.findByEmailKH(email);
    }

    public List<KhachHang> getAllKhachHang() {
        return khachHangRepository.findAll();
    }

    @Override
    public void save(KhachHang khachHang) {
        khachHangRepository.save(khachHang);
    }

    @Override
    public void deteleByIdKhachHang(UUID id) {
        khachHangRepository.deleteById(id);
    }

    @Override
    public KhachHang getByIdKhachHang(UUID id) {
        return khachHangRepository.findById(id).orElse(null);
    }

    @Override
    public void addKhachHang(KhachHang khachHang) {
        khachHangRepository.save(khachHang);
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
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH(row.getCell(0).getStringCellValue()); // Cột 0 trong tệp Excel
                khachHang.setHoTenKH(row.getCell(1).getStringCellValue()); // Cột 1 trong tệp Excel

                // Đối tượng Chức Vụ
                String LKHName = row.getCell(3).getStringCellValue();
                LoaiKhachHang loaiKhachHang = loaiKhachHangRepository.findByTenLKH(LKHName); // Tìm đối tượng ChatLieu theo tên
                khachHang.setLoaiKhachHang(loaiKhachHang);

//                nhanVien.set(row.getCell(4).getStringCellValue());
                khachHang.setTrangThai(1);
                khachHang.setTgThem(new Date());
                khachHangRepository.save(khachHang);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            // Xử lý lỗi nếu cần
        }
    }

    @Override
    public List<KhachHang> findByLoaiKhachHang(LoaiKhachHang loaiKhachHang) {
        return khachHangRepository.findByLoaiKhachHang(loaiKhachHang);
    }

    @Override
    public List<KhachHang> findByTrangThai(int trangThai) {
        return khachHangRepository.findByTrangThai(trangThai);
    }

    @Override
    public List<KhachHang> fillterKhachHang(String maKH, String tenKH) {
        if ("Mã Khách Hàng".equals(maKH) && "Tên Khách Hàng".equals(tenKH)) {
            return khachHangRepository.findAll();
        }
        return khachHangRepository.findByMaKHOrHoTenKH(maKH, tenKH);
    }

    @Override
    public List<KhachHang> findKhachHangByTrangThai() {
        return khachHangRepository.getKhachHangByTrangThai();
    }

    @Override
    public List<KhachHang> findKhachHangByKeyword(String keyword) {
        return khachHangRepository.findByHoTenKHOrSdtKH(keyword);

    }
}
