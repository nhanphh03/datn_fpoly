package com.example.demo.service.impls;

import com.example.demo.model.KhuyenMai;
import com.example.demo.model.LoaiKhuyenMai;
import com.example.demo.repository.KhuyenMaiRepository;
import com.example.demo.service.KhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class KhuyenMaiServiceImpls implements KhuyenMaiService {

    @Autowired
    private KhuyenMaiRepository khuyenMaiRepository;

    @Override
    public void createKhuyenMais(KhuyenMai khuyenMai) {
         khuyenMaiRepository.save(khuyenMai);
    }

    @Override
    public KhuyenMai findByID(UUID idKhuyenMai) {
        return khuyenMaiRepository.findById(idKhuyenMai).orElse(null);
    }

    @Override
    public List<KhuyenMai> getAllKhuyenMai() {
        return khuyenMaiRepository.findAll();
    }

    @Override
    public List<KhuyenMai> findByTrangThai(int trangThai) {
        return khuyenMaiRepository.findByTrangThai(trangThai);
    }

    @Override
    public List<KhuyenMai> findByLoaiKM(LoaiKhuyenMai loaiKhuyenMai) {
        return khuyenMaiRepository.findByLoaiKhuyenMai(loaiKhuyenMai);
    }

    @Override
    public List<KhuyenMai> findByLoaiKMAndTrangThai(LoaiKhuyenMai loaiKhuyenMai) {
        return khuyenMaiRepository.findByLoaiKhuyenMaiAndTrangThai(loaiKhuyenMai, 1);
    }

    @Override
    public List<KhuyenMai> findByNgayBatDauAndNgayKetThuc(Date ngayBatDau, Date ngayKetThuc) {
        return null;
    }

    @Override
    public KhuyenMai findByMaKM(String maKM) {
        return khuyenMaiRepository.findByMaKM(maKM);
    }
}
