package com.example.demo.repository.thanhdvph27747;

import com.example.demo.model.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DoanhThuThang extends JpaRepository<HoaDonChiTiet, UUID> {
    @Query(value = "select sum(Hoa_Don_Chi_Tiet.So_Luong*Hoa_Don_Chi_Tiet.don_gia_khi_giam) from hoa_don\n" +
            "join hoa_don_chi_tiet on hoa_don.id_hd=hoa_don_chi_tiet.id_hd where month(tg_thanh_toan) = month(GETDATE()) and year(tg_thanh_toan) = year(GETDATE())",
    nativeQuery = true) Double getDoanhThuThang();
}
