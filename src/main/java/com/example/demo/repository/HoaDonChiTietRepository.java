package com.example.demo.repository;

import com.example.demo.model.HoaDon;
import com.example.demo.model.HoaDonChiTiet;
import com.example.demo.viewModel.CTHDViewModel;
import com.example.demo.viewModel.HieuSuatBanHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(value = "select sum(tong_tien_da_giam) from hoa_don where month(tg_thanh_toan) = month(GETDATE())  and year(tg_thanh_toan) = year(GETDATE()) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3) or (loai_hd=0 and trang_thai=1 and hinh_thuc_thanh_toan=1))\n",
            nativeQuery = true)
    Optional<Double> getLaiThangNay();

    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet a join hoa_don b on a.id_hd=b.id_hd where month(b.tg_thanh_toan) = month(GETDATE()) \n" +
            "and year(b.tg_thanh_toan) = year(GETDATE()) and ((b.loai_hd=1 and b.trang_thai=1) or (b.loai_hd=0 and b.trang_thai = 3))\n" +
            "            and day(b.tg_thanh_toan) = day(GETDATE())",nativeQuery = true)
    Optional<Integer> getTongSPBanTrongNgay();

    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet a join hoa_don b on a.id_hd=b.id_hd where month(b.tg_thanh_toan) = month(GETDATE())  and year(b.tg_thanh_toan) = year(GETDATE()) and" +
            "((b.loai_hd=1 and b.trang_thai=1) or (b.loai_hd=0 and b.trang_thai = 3))",nativeQuery = true)
    Optional<Integer> getTongSPBanTrongThang();

<<<<<<< HEAD
    @Query(value = "select sum(tong_tien_da_giam) from hoa_don where (loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3) or (loai_hd=0 and trang_thai=1 and hinh_thuc_thanh_toan=1)",nativeQuery = true)
=======
    @Query(value = "select sum(tong_tien_da_giam) from hoa_don where month(tg_thanh_toan) = month(GETDATE())  and year(tg_thanh_toan) = year(GETDATE()) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3) or(loai_hd=0 and trang_thai=3 and hinh_thuc_thanh_toan=1))",nativeQuery = true)
>>>>>>> 220eec3b1d2df41c7e69050ea9d495b12244be5c
    Optional<Double> getTongTienLaiCuaHang();

    @Query(value = "select ((gia_nhap*b.so_luong)) from hoa_don a join hoa_don_chi_tiet b on a.id_hd=b.id_hd \n" +
            "join chi_tiet_giay c on b.id_ctg=c.id_chi_tiet_giay\n" +
            "where month(tg_thanh_toan) = month(GETDATE()) and year(tg_thanh_toan) = year(GETDATE()) and a.trang_thai=1",nativeQuery = true)
    Optional<Double> getTienGoc();

    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet join hoa_don h on h.id_hd=hoa_don_chi_tiet.id_hd where MONTH(tg_them) = 1 and ((h.loai_hd=1 and h.trang_thai=1) or (h.loai_hd=0 and h.trang_thai = 3))",nativeQuery = true)
    Integer getThang1();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet join hoa_don h on h.id_hd=hoa_don_chi_tiet.id_hd where MONTH(tg_them) = 2 and ((h.loai_hd=1 and h.trang_thai=1) or (h.loai_hd=0 and h.trang_thai = 3))",nativeQuery = true)
    Integer getThang2();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet join hoa_don h on h.id_hd=hoa_don_chi_tiet.id_hd where MONTH(tg_them) = 3 and ((h.loai_hd=1 and h.trang_thai=1) or (h.loai_hd=0 and h.trang_thai = 3))",nativeQuery = true)
    Integer getThang3();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet join hoa_don h on h.id_hd=hoa_don_chi_tiet.id_hd where MONTH(tg_them) = 4 and ((h.loai_hd=1 and h.trang_thai=1) or (h.loai_hd=0 and h.trang_thai = 3))",nativeQuery = true)
    Integer getThang4();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet join hoa_don h on h.id_hd=hoa_don_chi_tiet.id_hd where MONTH(tg_them) = 5 and ((h.loai_hd=1 and h.trang_thai=1) or (h.loai_hd=0 and h.trang_thai = 3))",nativeQuery = true)
    Integer getThang5();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet join hoa_don h on h.id_hd=hoa_don_chi_tiet.id_hd where MONTH(tg_them) = 6 and ((h.loai_hd=1 and h.trang_thai=1) or (h.loai_hd=0 and h.trang_thai = 3))",nativeQuery = true)
    Integer getThang6();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet join hoa_don h on h.id_hd=hoa_don_chi_tiet.id_hd where MONTH(tg_them) = 7 and ((h.loai_hd=1 and h.trang_thai=1) or (h.loai_hd=0 and h.trang_thai = 3))",nativeQuery = true)
    Integer getThang7();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet join hoa_don h on h.id_hd=hoa_don_chi_tiet.id_hd where MONTH(tg_them) = 8 and ((h.loai_hd=1 and h.trang_thai=1) or (h.loai_hd=0 and h.trang_thai = 3))",nativeQuery = true)
    Integer getThang8();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet join hoa_don h on h.id_hd=hoa_don_chi_tiet.id_hd where MONTH(tg_them) = 9 and ((h.loai_hd=1 and h.trang_thai=1) or (h.loai_hd=0 and h.trang_thai = 3))",nativeQuery = true)
    Integer getThang9();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet join hoa_don h on h.id_hd=hoa_don_chi_tiet.id_hd where MONTH(tg_them) = 10 and ((h.loai_hd=1 and h.trang_thai=1) or (h.loai_hd=0 and h.trang_thai = 3))",nativeQuery = true)
    Integer getThang10();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet join hoa_don h on h.id_hd=hoa_don_chi_tiet.id_hd where MONTH(tg_them) = 11 and ((h.loai_hd=1 and h.trang_thai=1) or (h.loai_hd=0 and h.trang_thai = 3))",nativeQuery = true)
    Integer getThang11();
    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet join hoa_don h on h.id_hd=hoa_don_chi_tiet.id_hd where MONTH(tg_them) = 12 and ((h.loai_hd=1 and h.trang_thai=1) or (h.loai_hd=0 and h.trang_thai = 3))",nativeQuery = true)
    Integer getThang12();

    @Query(value = "select sum(so_luong) from hoa_don_chi_tiet a join hoa_don b on a.id_hd=b.id_hd where month(b.tg_thanh_toan) = month(GETDATE()) \n" +
            "and year(b.tg_thanh_toan) = year(GETDATE()) and (b.loai_hd=1 and b.trang_thai=1) or (b.loai_hd=0 and b.trang_thai = 3)\n" +
            "            and day(b.tg_thanh_toan) = day(GETDATE())",nativeQuery = true)
    Integer getNgaythu1();

    @Query(value = "SELECT \n" +
            "sum(so_luong) from hoa_don_chi_tiet join hoa_don h on h.id_hd=hoa_don_chi_tiet.id_hd where year(tg_them) like 2022",nativeQuery = true)
    Integer Nam2022();

    @Query(value = "SELECT \n" +
            "sum(so_luong) from hoa_don_chi_tiet join hoa_don h on h.id_hd=hoa_don_chi_tiet.id_hd where year(tg_them) like 2023",nativeQuery = true)
    Integer Nam2023();

    @Query(value = "SELECT \n" +
            "sum(so_luong) from hoa_don_chi_tiet join hoa_don h on h.id_hd=hoa_don_chi_tiet.id_hd where year(tg_them) like 2024",nativeQuery = true)
    Integer Nam2024();

    @Query(value = "SELECT ctg.gia_ban,g.ten_giay ,hdct.don_gia , hdct.so_luong, ha.url1,hd.ten_nguoi_nhan,\n" +
            "hd.sdt_nguoi_nhan,\n" +
            "hd.dia_chi_nguoi_nhan FROM\n" +
            "hoa_don_chi_tiet hdct\n" +
            "join hoa_don hd on hdct.id_hd=hd.id_hd\n" +
            "join chi_tiet_giay ctg on ctg.id_chi_tiet_giay=hdct.id_ctg\n" +
            "join hinh_anh ha on ha.id_hinh_anh=ctg.id_hinh_anh\n" +
            "join giay g on g.id_giay=ctg.id_giay\n" +
            "WHERE ((loai_hd=1 and hd.trang_thai=1) or (hd.loai_hd=0 and hd.trang_thai = 3)) AND MONTH(hd.tg_thanh_toan) = MONTH (getdate()) and Day(tg_thanh_toan) = Day(getdate())",nativeQuery = true)
    List<Object[]> findHoaDonChiTietByDate();


    @Query(value = "SELECT ctg.gia_ban,g.ten_giay ,hdct.don_gia,hdct.so_luong,ha.url1,hd.ten_nguoi_nhan,\n" +
            "hd.sdt_nguoi_nhan,\n" +
            "hd.dia_chi_nguoi_nhan FROM\n" +
            "hoa_don_chi_tiet hdct\n" +
            "join hoa_don hd on hdct.id_hd=hd.id_hd\n" +
            "join chi_tiet_giay ctg on ctg.id_chi_tiet_giay=hdct.id_ctg\n" +
            "join hinh_anh ha on ha.id_hinh_anh=ctg.id_hinh_anh\n" +
            "join giay g on g.id_giay=ctg.id_giay\n" +
            "WHERE (loai_hd=1 and hd.trang_thai=1) or (hd.loai_hd=0 and hd.trang_thai = 3) AND MONTH(hd.tg_thanh_toan) = MONTH (getdate()) and year(hd.tg_thanh_toan) = year (getdate())",nativeQuery = true)
    List<Object[]> findHoaDonChiTietByMonth();

    @Query(value = "select n.ma_nv,n.ho_ten_nv,n.email_nv,SUM(cthd.so_luong) FROM chi_tiet_giay ctg \n" +
            "                        JOIN Giay g ON g.id_giay = ctg.id_giay\n" +
            "                        JOIN hinh_anh a ON a.id_hinh_anh = ctg.id_hinh_anh\n" +
            "                        JOIN mau_sac ms ON ms.id_mau = ctg.id_mau\n" +
            "\t\t\t\t\t\tLEFT JOIN hoa_don_chi_tiet cthd ON cthd.id_ctg = ctg.id_chi_tiet_giay\n" +
            "\t\t\t\t\t\tleft JOIN hoa_don hd ON hd.id_hd =cthd.id_hd\n" +
            "\t\t\t\t\t\t join nhan_vien n on n.id_nhan_vien =hd.id_nv\n" +
            "            where MONTH(hd.tg_thanh_toan) =MONTH(GETDATE())\n" +
            "\t\t\tand (loai_hd=1 and hd.trang_thai=1) or (hd.loai_hd=0 and hd.trang_thai = 3)\n" +
            "            group by n.ma_nv,n.ho_ten_nv,n.email_nv\n" +
            "\t\t\torder by SUM(cthd.so_luong) desc",nativeQuery = true)
    List<Object[]> getHieuSuatNhanVienBanHang();

    @Query("select g from HoaDonChiTiet g where g.hoaDon.nhanVien.maNV =:maNV")
    List<HoaDonChiTiet> getChiTietSPNhanVienBan( String maNV);
    //code chart thống kê doanh thu
    @Query(value = "select  sum(tong_tien_da_giam) from hoa_don where (MONTH(tg_thanh_toan) = 1) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienDaGiamThang1();
    @Query(value = "select  sum(tong_tien_da_giam) from hoa_don where (MONTH(tg_thanh_toan) = 2) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienDaGiamThang2();
    @Query(value = "select  sum(tong_tien_da_giam) from hoa_don where (MONTH(tg_thanh_toan) = 3) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienDaGiamThang3();
    @Query(value = "select  sum(tong_tien_da_giam) from hoa_don where (MONTH(tg_thanh_toan) = 4) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienDaGiamThang4();
    @Query(value = "select  sum(tong_tien_da_giam) from hoa_don where (MONTH(tg_thanh_toan) = 5) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienDaGiamThang5();
    @Query(value = "select  sum(tong_tien_da_giam) from hoa_don where (MONTH(tg_thanh_toan) = 6) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienDaGiamThang6();
    @Query(value = "select  sum(tong_tien_da_giam) from hoa_don where (MONTH(tg_thanh_toan) = 7) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienDaGiamThang7();
    @Query(value = "select  sum(tong_tien_da_giam) from hoa_don where (MONTH(tg_thanh_toan) = 8) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienDaGiamThang8();
    @Query(value = "select  sum(tong_tien_da_giam) from hoa_don where (MONTH(tg_thanh_toan) = 9) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienDaGiamThang9();
    @Query(value = "select  sum(tong_tien_da_giam) from hoa_don where (MONTH(tg_thanh_toan) = 10) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienDaGiamThang10();
    @Query(value = "select  sum(tong_tien_da_giam) from hoa_don where (MONTH(tg_thanh_toan) = 11) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienDaGiamThang11();
    @Query(value = "select  sum(tong_tien_da_giam) from hoa_don where (MONTH(tg_thanh_toan) = 12) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienDaGiamThang12();

    @Query(value = "select  sum(tong_tien) from hoa_don where (MONTH(tg_thanh_toan) = 1) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienThang1();
    @Query(value = "select  sum(tong_tien) from hoa_don where (MONTH(tg_thanh_toan) = 2) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienThang2();
    @Query(value = "select  sum(tong_tien) from hoa_don where (MONTH(tg_thanh_toan) = 3) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienThang3();
    @Query(value = "select  sum(tong_tien) from hoa_don where (MONTH(tg_thanh_toan) = 4) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienThang4();
    @Query(value = "select  sum(tong_tien) from hoa_don where (MONTH(tg_thanh_toan) = 5) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienThang5();
    @Query(value = "select  sum(tong_tien) from hoa_don where (MONTH(tg_thanh_toan) = 6) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienThang6();
    @Query(value = "select  sum(tong_tien) from hoa_don where (MONTH(tg_thanh_toan) = 7) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienThang7();
    @Query(value = "select  sum(tong_tien) from hoa_don where (MONTH(tg_thanh_toan) = 8) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienThang8();
    @Query(value = "select  sum(tong_tien) from hoa_don where (MONTH(tg_thanh_toan) = 9) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienThang9();
    @Query(value = "select  sum(tong_tien) from hoa_don where (MONTH(tg_thanh_toan) = 10) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienThang10();
    @Query(value = "select  sum(tong_tien) from hoa_don where (MONTH(tg_thanh_toan) = 11) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienThang11();
    @Query(value = "select  sum(tong_tien) from hoa_don where (MONTH(tg_thanh_toan) = 12) and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) and year(tg_thanh_toan)=year(getdate())",nativeQuery = true)
    Double getTongTienThang12();

    @Query(value = "select  sum(tong_tien) from hoa_don where year (tg_thanh_toan) = 2021 and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) ",nativeQuery = true)
    Double getTongTienNam2021();
    @Query(value = "select  sum(tong_tien) from hoa_don where year(tg_thanh_toan) = 2022 and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) ",nativeQuery = true)
    Double getTongTienNam2022();
    @Query(value = "select  sum(tong_tien) from hoa_don where year(tg_thanh_toan) = 2023 and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) ",nativeQuery = true)
    Double getTongTienNam2023();
    @Query(value = "select  sum(tong_tien) from hoa_don where year(tg_thanh_toan) = 2024 and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) ",nativeQuery = true)
    Double getTongTienNam2024();
    @Query(value = "select  sum(tong_tien) from hoa_don where year(tg_thanh_toan) = 2025 and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3))",nativeQuery = true)
    Double getTongTienNam2025();

    @Query(value = "select  sum(tong_tien_da_giam) from hoa_don where year(tg_thanh_toan) = 2021 and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3))",nativeQuery = true)
    Double getTongTienDaGiamNam2021();
    @Query(value = "select  sum(tong_tien_da_giam) from hoa_don where year(tg_thanh_toan) = 2022 and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) ",nativeQuery = true)
    Double getTongTienDaGiamNam2022();
    @Query(value = "select  sum(tong_tien_da_giam) from hoa_don where year(tg_thanh_toan) = 2023 and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) ",nativeQuery = true)
    Double getTongTienDaGiamNam2023();
    @Query(value = "select  sum(tong_tien_da_giam) from hoa_don where year(tg_thanh_toan) = 2024 and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) ",nativeQuery = true)
    Double getTongTienDaGiamNam2024();
    @Query(value = "select  sum(tong_tien_da_giam) from hoa_don where year(tg_thanh_toan) = 2025 and ((loai_hd=1 and trang_thai=1) or (loai_hd=0 and trang_thai = 3)) ",nativeQuery = true)
    Double getTongTienDaGiamNam2025();


}