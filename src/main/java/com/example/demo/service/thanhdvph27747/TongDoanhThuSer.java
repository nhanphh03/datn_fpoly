package com.example.demo.service.thanhdvph27747;

import com.example.demo.repository.thanhdvph27747.TongDoanhThu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TongDoanhThuSer {
    @Autowired
    private TongDoanhThu tongDoanhThu;

    public Double TongDoanhThu(){
        return tongDoanhThu.getTongTien();
    }
}
