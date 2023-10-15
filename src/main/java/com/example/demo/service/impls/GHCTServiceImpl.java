package com.example.demo.service.impls;

import com.example.demo.model.GioHang;
import com.example.demo.model.GioHangChiTiet;
import com.example.demo.repository.GHCTRepository;
import com.example.demo.service.GHCTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GHCTServiceImpl implements GHCTService {


    @Autowired
    private GHCTRepository ghctRepository;

    @Override
    public List<GioHangChiTiet> findByGHActive(GioHang gioHang) {
        return ghctRepository.findByTrangThaiAndAndGioHang(1, gioHang);
    }

    @Override
    public void addNewGHCT(GioHangChiTiet gioHangChiTiet) {
        ghctRepository.save(gioHangChiTiet);
    }
}
