package com.example.demo.service;

import com.example.demo.model.Giay;
import com.example.demo.model.KhachHang;
import com.example.demo.model.LuotXemFA;

import java.util.List;
import java.util.UUID;

public interface LuotXemFAService {

    void addNewLuotXem(LuotXemFA luotXemFA);

    List<LuotXemFA> getAllActiveByFAOrRV(KhachHang khachHang, int loai);

    LuotXemFA checkLuotXemOrFA(KhachHang khachHang, Giay giay, int loai, UUID maMau);
}
