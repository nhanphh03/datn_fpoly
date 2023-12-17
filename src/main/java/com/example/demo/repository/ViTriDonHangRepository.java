package com.example.demo.repository;


import com.example.demo.model.GiaoHang;
import com.example.demo.model.ViTriDonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ViTriDonHangRepository extends JpaRepository<ViTriDonHang, UUID> {

    List<ViTriDonHang> findByGiaoHangOrderByThoiGianDesc(GiaoHang giaoHang);

}
