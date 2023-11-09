package com.example.demo.service.thanhdvph27747;

import com.example.demo.repository.thanhdvph27747.DoanhThuThang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoanhThuThangSer {
    @Autowired
    private DoanhThuThang doanhThuThang;

    public Double getDoanhThuThang(){
        return doanhThuThang.getDoanhThuThang();
    }
}
