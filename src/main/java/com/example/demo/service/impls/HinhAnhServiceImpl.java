package com.example.demo.service.impls;

import com.example.demo.model.HinhAnh;
import com.example.demo.repository.HinhAnhRepository;
import com.example.demo.service.HinhAnhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HinhAnhServiceImpl implements HinhAnhService {
    @Autowired
    private HinhAnhRepository hinhAnhRepository;

    @Override
    public List<HinhAnh> getAllHinhAnh() {
        return hinhAnhRepository.findAllByOrderByTgThemDesc();
    }

    @Override
    public void save(HinhAnh hinhAnh) {
        hinhAnhRepository.save(hinhAnh);
    }

    @Override
    public void deleteByIdHinhAnh(UUID id) {
        hinhAnhRepository.deleteById(id);
    }

    @Override
    public HinhAnh getByIdHinhAnh(UUID id) {
        return hinhAnhRepository.findById(id).orElse(null);
    }

    @Override
    public List<HinhAnh> filterHinhAnh(String ma) {
        if ("Mã".equals(ma)) {
            return hinhAnhRepository.findAll();
        }
        return hinhAnhRepository.findMa(ma);
    }
}
