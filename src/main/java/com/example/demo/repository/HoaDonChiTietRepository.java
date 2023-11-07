package com.example.demo.repository;

import com.example.demo.model.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, UUID> {
    @Query(value = "select hdct from HoaDonChiTiet hdct where hdct.hoaDon.idHD = ?1 and hdct.chiTietGiay.idCTG =?2")
    HoaDonChiTiet findByIdHoaDonAndIdChiTietGiay(UUID idHoaDon, UUID idChiTietGiay);

    @Query(value = "select * from hoa_don_chi_tiet where id_hd = ?1 and trang_thai = 1", nativeQuery = true)
    List<HoaDonChiTiet> findByIdHoaDon(UUID idHoaDon);

    @Query(value = "select top 6 hct.id_ctg, g.ten_giay, s.so_size, h.ten_hang, cl.ten_chat_lieu, ms.ten_mau, SUM(hct.so_luong) AS so_luong_ban\n" +
            "from hoa_don_chi_tiet hct\n" +
            "join chi_tiet_giay ctg on hct.id_ctg = ctg.id_chi_tiet_giay\n" +
            "join giay g on ctg.id_giay = g.id_giay\n" +
            "join size s on ctg.id_size = s.id_size\n" +
            "join mau_sac ms on ctg.id_mau = ms.id_mau\n" +
            "join hang h on g.id_hang = h.id_hang\n" +
            "join chat_lieu cl on g.id_chat_lieu = cl.id_chat_lieu\n" +
            "GROUP BY hct.id_ctg, g.ten_giay, s.so_size, h.ten_hang, cl.ten_chat_lieu, ms.ten_mau\n" +
            "ORDER BY so_luong_ban DESC;", nativeQuery = true)
    List<Object[]> spBanChay();
}
