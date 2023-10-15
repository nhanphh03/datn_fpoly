package com.example.demo.service.impls;

import com.example.demo.model.Giay;
import com.example.demo.model.KhachHang;
import com.example.demo.model.LuotXemFA;
import com.example.demo.repository.LuotXemFARepository;
import com.example.demo.service.LuotXemFAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LuotXemFAServiceImpl implements LuotXemFAService {

    @Autowired
    private LuotXemFARepository luotXemFARepository;

    @Override
    public void addNewLuotXem(LuotXemFA luotXemFA) {
        luotXemFARepository.save(luotXemFA);
    }

    @Override
    public List<LuotXemFA> getAllActiveByFAOrRV(KhachHang khachHang, int loai) {
        return  luotXemFARepository.findByKhachHangAndTrangThaiAndLoaiOrderByTgThemDesc(khachHang, 1, loai);
    }

    @Override
    public LuotXemFA checkLuotXemOrFA(KhachHang khachHang, Giay giay, int loai) {
        return luotXemFARepository.findByKhachHangAndGiayAndTrangThaiAndLoai(khachHang,giay, 1, loai);
    }


}
