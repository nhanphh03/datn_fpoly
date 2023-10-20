package com.example.demo.service.impls;

import com.example.demo.model.ChucVu;
import com.example.demo.repository.ChucVuRepsitory;
import com.example.demo.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
