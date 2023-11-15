package com.example.demo.service;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.KhuyenMaiChiTietCTG;


public interface KhuyenMaiChiTietProductService {

    void addKMCTSP(KhuyenMaiChiTietCTG khuyenMaiChiTietCTG);

    void removeKMCTSP(KhuyenMaiChiTietCTG khuyenMaiChiTietCTG);


    KhuyenMaiChiTietCTG findByIDCTSP(ChiTietGiay chiTietGiay);

}
