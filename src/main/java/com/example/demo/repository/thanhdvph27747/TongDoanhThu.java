package com.example.demo.repository.thanhdvph27747;

import com.example.demo.model.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TongDoanhThu extends JpaRepository<HoaDonChiTiet, UUID> {
    @Query(value = "select sum(don_gia_khi_giam*so_luong) from hoa_don_chi_tiet",nativeQuery = true)
    Double getTongTien();
}
