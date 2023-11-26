package com.example.demo.service.impls;


import com.example.demo.model.ChucVu;
import com.example.demo.model.DiaChiKH;
import com.example.demo.model.KhachHang;
import com.example.demo.model.NhanVien;
import com.example.demo.repository.DiaChiRepsitory;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.service.DiaChiKHService;
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
public class DiaChiServicelmpl implements DiaChiKHService {

    @Autowired
    private DiaChiRepsitory diaChiRepsitory;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public List<DiaChiKH> getAllDiaChiKH() {
        return diaChiRepsitory.findAll();
    }

    @Override
    public void save(DiaChiKH diaChiKH) {
        diaChiRepsitory.save(diaChiKH) ;
    }

    @Override
    public void deleteByIdDiaChiKH(UUID id) {
        diaChiRepsitory.deleteById(id);
    }

    @Override
    public DiaChiKH getByIdDiaChikh(UUID id) {
        return diaChiRepsitory.findById(id).orElse(null);
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
                DiaChiKH diaChiKH = new DiaChiKH();
                diaChiKH.setMaDC(row.getCell(0).getStringCellValue()); // Cột 0 trong tệp Excel
                diaChiKH.setTenDC(row.getCell(1).getStringCellValue()); // Cột 1 trong tệp Excel

                // Đối tượng Chức Vụ
                String khachHangName = row.getCell(3).getStringCellValue();
                KhachHang khachHang = khachHangRepository.findByHoTenKH(khachHangName); // Tìm đối tượng ChatLieu theo tên
                diaChiKH.setKhachHang(khachHang);

//                diaChiKH.set(row.getCell(4).getStringCellValue());
                diaChiKH.setTrangThai(1);
                diaChiKH.setTgThem(new Date());
                diaChiRepsitory.save(diaChiKH);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            // Xử lý lỗi nếu cần
        }
    }

    @Override
    public List<DiaChiKH> findbyKhachHangAndLoaiAndTrangThai(KhachHang khachHang, Boolean loai, Integer trangThai) {
        return diaChiRepsitory.findByKhachHangAndLoaiAndTrangThai(khachHang, loai, trangThai);
    }

    @Override
    public DiaChiKH findDCKHDefaulByKhachHang(KhachHang khachHang) {
        return diaChiRepsitory.findByKhachHangAndLoai(khachHang, true);
    }

    @Override
    public List<DiaChiKH> findByKhachHang(KhachHang khachHang) {
        return diaChiRepsitory.findByKhachHang(khachHang);
    }

    @Override
    public List<DiaChiKH> findByTrangThai(int trangThai) {
        return diaChiRepsitory.findByTrangThai(trangThai);
    }

    @Override
    public List<DiaChiKH> fillterDiaChiKH(String maDC, String tenDC) {
        if ("Mã Địa Chỉ".equals(maDC) && "Tên Địa Chỉ".equals(tenDC)) {
            return diaChiRepsitory.findAll();
        }
        return diaChiRepsitory.findByMaDCOrTenDC(maDC, maDC);
    }

    @Override
    public List<DiaChiKH> findByKhachHangAndTrangThai(KhachHang khachHang, int trangThai) {
        return diaChiRepsitory.findByKhachHangAndTrangThai(khachHang, trangThai);
    }

    @Override
    public DiaChiKH findByIdDiaChiKH(UUID idDCKH) {
        return diaChiRepsitory.findById(idDCKH).orElse(null);
    }

    @Override
    public List<DiaChiKH> getDiaChibyKhachHang(KhachHang khachHang) {
        return diaChiRepsitory.findByKhachHang(khachHang);
    }

}
