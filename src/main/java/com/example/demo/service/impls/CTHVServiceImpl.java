package com.example.demo.service.impls;

import com.example.demo.model.ChiTietHVKH;
import com.example.demo.repository.ChiTietHVKHRepository;
import com.example.demo.service.CTHVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CTHVServiceImpl implements CTHVService {

    @Autowired
    private ChiTietHVKHRepository chiTietHVKHRepository;

    @Override
    public void addnewcthv(ChiTietHVKH chiTietHVKH) {
        chiTietHVKHRepository.save(chiTietHVKH);
    }
}
