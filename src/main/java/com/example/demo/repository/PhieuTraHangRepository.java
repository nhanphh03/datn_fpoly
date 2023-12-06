package com.example.demo.repository;

import com.example.demo.model.HoaDon;
import com.example.demo.model.PhieuTraHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PhieuTraHangRepository extends JpaRepository<PhieuTraHang, UUID> {

    PhieuTraHang findByHoaDon(HoaDon hoaDon);

}
