package com.example.demo.service.impls;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.KhuyenMaiChiTietCTG;
import com.example.demo.repository.KMCTProductRepository;
import com.example.demo.service.KhuyenMaiChiTietProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KMCTProductServiceServiceImpls implements KhuyenMaiChiTietProductService {

    @Autowired
    private KMCTProductRepository kmctProductRepository;

    @Override
    public void addKMCTSP(KhuyenMaiChiTietCTG khuyenMaiChiTietCTG) {
        kmctProductRepository.save(khuyenMaiChiTietCTG);
    }

    @Override
    public void removeKMCTSP(KhuyenMaiChiTietCTG khuyenMaiChiTietCTG) {
        kmctProductRepository.delete(khuyenMaiChiTietCTG);
    }

    @Override
    public KhuyenMaiChiTietCTG findByIDCTSP(ChiTietGiay chiTietGiay) {
        return kmctProductRepository.findByChiTietGiay(chiTietGiay);
    }
}
