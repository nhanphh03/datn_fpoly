package com.example.demo.repository;

import com.example.demo.model.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon , UUID> {
    @Query(value = "select * from Hoa_Don where trang_thai = 1 ",nativeQuery = true)
    List<HoaDon> listChuaThanhToan();
}
