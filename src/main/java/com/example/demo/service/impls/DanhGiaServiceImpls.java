package com.example.demo.service.impls;

import com.example.demo.model.DanhGiaKhachHang;
import com.example.demo.model.Giay;
import com.example.demo.model.HoaDon;
import com.example.demo.repository.DanhGiaRepositorty;
import com.example.demo.service.DanhGiaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanhGiaServiceImpls implements DanhGiaServices {

    @Autowired
    private DanhGiaRepositorty danhGiaRepositorty;

    @Override
    public void addDanhGia(DanhGiaKhachHang danhGiaKhachHang) {
        danhGiaRepositorty.save(danhGiaKhachHang);
    }

    @Override
    public List<DanhGiaKhachHang> findByGiay(Giay giay) {
        return danhGiaRepositorty.findByGiay(giay);
    }

    @Override
    public DanhGiaKhachHang findByHoaDon(HoaDon hoaDon) {
        return danhGiaRepositorty.findByHoaDon(hoaDon);
    }
}
