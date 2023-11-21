package com.example.demo.repository;

import com.example.demo.model.HoaDon;
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

    List<HoaDonChiTiet> findByHoaDonAndTrangThai(HoaDon hoaDon, int trangThai);

    List<HoaDonChiTiet> findByHoaDon(HoaDon hoaDon);

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

    @Query(value = "select sum(tong_tien) from hoa_don where month(tg_thanh_toan) = month(GETDATE()) and year(tg_thanh_toan) = year(GETDATE()) and trang_thai=1",
            nativeQuery = true) Double getDoanhThuThang();

    @Query(value = "select * from hoa_don_chi_tiet hdct join hoa_don hd on hdct.id_hd=hd.id_hd where hd.trang_thai=1",nativeQuery = true)
    List<String> getAllHoaDonDaThanhToan();

    @Query(value = "select * from hoa_don_chi_tiet hdct join hoa_don hd on hdct.id_hd=hd.id_hd where hd.trang_thai=0",nativeQuery = true)
    List<String> getAllHoaDonChoThanhToan();

    @Query(value = "select sum(tong_tien) from hoa_don where trang_thai=1",nativeQuery = true)
    Double getTongTien();

    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet where MONTH(tg_them) = 1",nativeQuery = true)
    Integer getThang1();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet where MONTH(tg_them) = 2",nativeQuery = true)
    Integer getThang2();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet where MONTH(tg_them) = 3",nativeQuery = true)
    Integer getThang3();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet where MONTH(tg_them) = 4",nativeQuery = true)
    Integer getThang4();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet where MONTH(tg_them) = 5",nativeQuery = true)
    Integer getThang5();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet where MONTH(tg_them) = 6",nativeQuery = true)
    Integer getThang6();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet where MONTH(tg_them) = 7",nativeQuery = true)
    Integer getThang7();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet where MONTH(tg_them) = 8",nativeQuery = true)
    Integer getThang8();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet where MONTH(tg_them) = 9",nativeQuery = true)
    Integer getThang9();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet where MONTH(tg_them) = 10",nativeQuery = true)
    Integer getThang10();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet where MONTH(tg_them) = 11",nativeQuery = true)
    Integer getThang11();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet where MONTH(tg_them) = 12",nativeQuery = true)
    Integer getThang12();

//    @Query(value = "select top 5 a.url1,g.ten_giay,ctg.gia_ban, sum(hdct.so_luong) as topsp from hoa_don_chi_tiet hdct join chi_tiet_giay ctg on hdct.id_ctg=ctg.id_chi_tiet_giay\n" +
//            "join giay g on ctg.id_giay=g.id_giay\n" +
//            "join hinh_anh a on ctg.id_hinh_anh=a.id_hinh_anh\n" +
//            "group by a.url1,g.ten_giay,ctg.gia_ban\n" +
//            "order by topsp desc",nativeQuery = true)
//    List<Object[]> getTop5();
}
