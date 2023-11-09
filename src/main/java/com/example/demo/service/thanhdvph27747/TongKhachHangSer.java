package com.example.demo.service.thanhdvph27747;

import com.example.demo.model.KhachHang;
import com.example.demo.repository.thanhdvph27747.TongKhachHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TongKhachHangSer {
    @Autowired
    private TongKhachHang tongKhachHang;

    public Integer getTong(){
        return tongKhachHang.getTongKH();
    }
}
