package com.example.demo.service.impls;

import com.example.demo.model.ChucVu;
import com.example.demo.model.NhanVien;
import com.example.demo.repository.NhanVienRepsitory;
import com.example.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NhanVienServiceImpl implements NhanVienService {
    @Autowired
    private NhanVienRepsitory nhanVienRepsitory;

    @Override
    public NhanVien checkByEmailAndChucVuAndPass(String email, String pass, ChucVu chucVu) {
        return nhanVienRepsitory.findByEmailNVAndMatKhauAndChucVuAndTrangThai(email, pass, chucVu, 1);
    }

    @Override
    public NhanVien checkBySDTAndChucVuAndPass(String sdt, String pass, ChucVu chucVu) {
        return nhanVienRepsitory.findBySdtNVAndMatKhauAndChucVuAndTrangThai(sdt, pass, chucVu, 1);
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
}
