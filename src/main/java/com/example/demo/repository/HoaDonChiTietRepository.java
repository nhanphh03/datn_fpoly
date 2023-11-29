package com.example.demo.repository;

import com.example.demo.model.HoaDon;
import com.example.demo.model.HoaDonChiTiet;
import com.example.demo.viewModel.CTHDViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
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

    @Query(value = "select sum(tong_tien) from hoa_don a join hoa_don_chi_tiet b on a.id_hd=b.id_hd \n" +
            "join chi_tiet_giay c on b.id_ctg=c.id_chi_tiet_giay\n" +
            "where month(tg_thanh_toan) = month(GETDATE())  and year(tg_thanh_toan) = year(GETDATE()) and a.trang_thai=1",
            nativeQuery = true)
    Optional<Double> getLaiThangNay();

    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet a join hoa_don b on a.id_hd=b.id_hd where month(b.tg_thanh_toan) = month(GETDATE())  and year(b.tg_thanh_toan) = year(GETDATE()) and b.trang_thai=1" +
            "and day(b.tg_thanh_toan) = day(GETDATE()) ",nativeQuery = true)
    Optional<Integer> getTongSPBanTrongNgay();

    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet a join hoa_don b on a.id_hd=b.id_hd where month(b.tg_thanh_toan) = month(GETDATE())  and year(b.tg_thanh_toan) = year(GETDATE()) and" +
            " b.trang_thai=1",nativeQuery = true)
    Optional<Integer> getTongSPBanTrongThang();

    @Query(value = "select sum(tong_tien) from hoa_don where trang_thai=1",nativeQuery = true)
    Optional<Double> getTongTienLaiCuaHang();

    @Query(value = "select ((gia_nhap*b.so_luong)) from hoa_don a join hoa_don_chi_tiet b on a.id_hd=b.id_hd \n" +
            "join chi_tiet_giay c on b.id_ctg=c.id_chi_tiet_giay\n" +
            "where month(tg_thanh_toan) = month(GETDATE()) and year(tg_thanh_toan) = year(GETDATE()) and a.trang_thai=1",nativeQuery = true)
   Optional<Double> getTienGoc();

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

    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet where MONTH(tg_them) = MONTH (getdate()) and Day(tg_them) = Day(getdate())",nativeQuery = true)
    Integer getNgaythu1();

    @Query(value = "SELECT \n" +
            "sum(so_luong) from hoa_don_chi_tiet where year(tg_them) like 2023",nativeQuery = true)
    Integer Nam2023();

    @Query(value = "SELECT \n" +
            "sum(so_luong) from hoa_don_chi_tiet where year(tg_them) like 2022",nativeQuery = true)
    Integer Nam2022();

    @Query(value = "SELECT \n" +
            "sum(so_luong) from hoa_don_chi_tiet where year(tg_them) like 2021",nativeQuery = true)
    Integer Nam2021();

    @Query(value = "SELECT ctg.gia_ban,g.ten_giay ,hdct.don_gia,hdct.so_luong,ha.url1,hd.ten_nguoi_nhan,\n" +
            "hd.sdt_nguoi_nhan,\n" +
            "hd.dia_chi_nguoi_nhan FROM\n" +
            "hoa_don_chi_tiet hdct\n" +
            "join hoa_don hd on hdct.id_hd=hd.id_hd\n" +
            "join chi_tiet_giay ctg on ctg.id_chi_tiet_giay=hdct.id_ctg\n" +
            "join hinh_anh ha on ha.id_hinh_anh=ctg.id_hinh_anh\n" +
            "join giay g on g.id_giay=ctg.id_giay\n" +
            "WHERE hd.trang_thai = 1 AND MONTH(hd.tg_thanh_toan) = MONTH (getdate()) and Day(tg_thanh_toan) = Day(getdate())",nativeQuery = true)
    List<Object[]> findHoaDonChiTietByDate();





    @Query(value = "SELECT ctg.gia_ban,g.ten_giay ,hdct.don_gia,hdct.so_luong,ha.url1,hd.ten_nguoi_nhan,\n" +
            "hd.sdt_nguoi_nhan,\n" +
            "hd.dia_chi_nguoi_nhan FROM\n" +
            "hoa_don_chi_tiet hdct\n" +
            "join hoa_don hd on hdct.id_hd=hd.id_hd\n" +
            "join chi_tiet_giay ctg on ctg.id_chi_tiet_giay=hdct.id_ctg\n" +
            "join hinh_anh ha on ha.id_hinh_anh=ctg.id_hinh_anh\n" +
            "join giay g on g.id_giay=ctg.id_giay\n" +
            "WHERE hd.trang_thai = 1 AND MONTH(hd.tg_thanh_toan) = MONTH (getdate()) and year(hd.tg_thanh_toan) = year (getdate())",nativeQuery = true)
    List<Object[]> findHoaDonChiTietByMonth();
}
