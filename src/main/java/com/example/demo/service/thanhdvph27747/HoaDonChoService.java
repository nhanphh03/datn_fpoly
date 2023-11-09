package com.example.demo.service.thanhdvph27747;

import com.example.demo.model.Giay;
import com.example.demo.model.HoaDon;
import com.example.demo.model.HoaDonChiTiet;
import com.example.demo.repository.thanhdvph27747.ChiSoHoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HoaDonChoService  {
@Autowired
    private ChiSoHoaDon chiSoHoaDon;

public Integer getAllHoaDon(){
    return chiSoHoaDon.getAllHoaDonDaThanhToan();
}
    public Integer getAllHoaDonCho(){
        return chiSoHoaDon.getAllHoaDonCho();
    }
}
