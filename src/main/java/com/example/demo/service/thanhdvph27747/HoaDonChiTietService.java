package com.example.demo.service.thanhdvph27747;

import com.example.demo.model.HoaDonChiTiet;
import com.example.demo.repository.thanhdvph27747.HoaDonChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonChiTietService {
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    public  List<HoaDonChiTiet> getAllHDChuaThanhToan(){
        return hoaDonChiTietRepository.getAllHoaDonChoThanhToan();
    }

    public List<HoaDonChiTiet> getAllHDDaThanhToan(){
        return hoaDonChiTietRepository.getAllHoaDonDaThanhToan();
    }
}
