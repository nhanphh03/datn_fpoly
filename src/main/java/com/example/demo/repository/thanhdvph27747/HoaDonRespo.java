package com.example.demo.repository.thanhdvph27747;

import com.example.demo.model.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface HoaDonRespo extends JpaRepository<HoaDon, UUID> {
    @Query("select g from HoaDon g where g.trangThai = 1 and g.trangThai=2")
    List<HoaDon> getAllHoaDonDaThanhToan();

}
