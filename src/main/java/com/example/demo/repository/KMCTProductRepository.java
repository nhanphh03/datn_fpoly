package com.example.demo.repository;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.KhuyenMaiChiTietCTG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KMCTProductRepository extends JpaRepository<KhuyenMaiChiTietCTG, UUID> {

    KhuyenMaiChiTietCTG findByChiTietGiay(ChiTietGiay chiTietGiay);

    KhuyenMaiChiTietCTG findByTenKM(String tenKM);
}
