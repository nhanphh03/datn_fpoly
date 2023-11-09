package com.example.demo.service.impls;

import com.example.demo.model.LoaiKhuyenMai;
import com.example.demo.repository.LoaiKhuyenMaiRepository;
import com.example.demo.service.LoaiKhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LoaiKhuyenMaiServiceImpls implements LoaiKhuyenMaiService {
    @Autowired
    private LoaiKhuyenMaiRepository loaiKhuyenMaiRepository;

    @Override
    public List<LoaiKhuyenMai> getAllLKM() {
        return loaiKhuyenMaiRepository.findAll();
    }

    @Override
    public void createLKM(LoaiKhuyenMai loaiKhuyenMai) {
         loaiKhuyenMaiRepository.save(loaiKhuyenMai);
    }

    @Override
    public LoaiKhuyenMai findByMaLKM(String maLKH) {
        return loaiKhuyenMaiRepository.findByMaLKM(maLKH);
    }

    @Override
    public LoaiKhuyenMai findByIDLKM(UUID idLKM) {
        return loaiKhuyenMaiRepository.findById(idLKM).orElse(null);
    }
}
