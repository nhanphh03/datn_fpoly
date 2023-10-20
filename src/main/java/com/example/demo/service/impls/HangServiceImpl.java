package com.example.demo.service.impls;

import com.example.demo.model.Hang;
import com.example.demo.repository.HangRepository;
import com.example.demo.service.HangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HangServiceImpl implements HangService {
    @Autowired
    private HangRepository hangRepository;

    @Override
    public List<Hang> getALlHang() {
        return hangRepository.findAll();
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
}
