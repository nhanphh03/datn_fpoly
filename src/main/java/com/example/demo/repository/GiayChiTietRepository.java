package com.example.demo.repository;

import com.example.demo.model.*;
import com.example.demo.utils.BaoHanhDTO;
import com.example.demo.viewModel.CTGViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GiayChiTietRepository extends JpaRepository<ChiTietGiay, UUID> {

    List<ChiTietGiay> findByGiay(Giay giay);

    List<ChiTietGiay> findByTrangThaiAndGiay(int trangThai, Giay giay);

    @Query(value = "SELECT DISTINCT ctg.hinhAnh FROM ChiTietGiay ctg WHERE ctg.giay = ?1 AND ctg.mauSac = ?2")
    HinhAnh findDistinctByGiay(Giay giay, MauSac mauSac);

    @Query("SELECT g FROM ChiTietGiay g WHERE g.size.soSize = :searchTerm OR g.mauSac.tenMau = :searchTerm")
    List<ChiTietGiay> customSearchCTG(@Param("searchTerm") String searchTerm);

    @Query("SELECT g FROM ChiTietGiay g WHERE g.size.soSize = :searchTerm OR g.mauSac.tenMau = :searchTerm OR g.giay.tenGiay = :searchTerm")
    List<ChiTietGiay> customSearchGCT(@Param("searchTerm") String searchTerm);

    List<ChiTietGiay> findBySize(Size size);

    List<ChiTietGiay> findByMauSac(MauSac mauSac);

    @Query(value = "SELECT DISTINCT ctg.size FROM ChiTietGiay ctg WHERE ctg.giay = ?1 AND ctg.trangThai = 1 AND ctg.mauSac = ?2 ORDER BY ctg.size.soSize")
    List<Size> findDistinctSizeByGiayAndTrangThai(Giay giay, MauSac mauSac);

    @Query(value = "SELECT DISTINCT ctg.mauSac FROM ChiTietGiay ctg WHERE ctg.giay = ?1 AND ctg.trangThai = 1 ")
    List<MauSac> findDistinctMauSacByGiayAndTrangThai(Giay giay);

    List<ChiTietGiay> findByMauSacAndGiayAndTrangThai(MauSac mauSac, Giay giay, int trangThai);

    List<ChiTietGiay> findByGiayAndMauSac(Giay giay, MauSac mauSac);

    List<ChiTietGiay> findAllByOrderByTgThemDesc();

    @Query(value = "SELECT p.id_chi_tiet_giay,g.ma_giay ,g.ten_giay,h.ten_hang, p.nam_bao_hanh \n" +
            "FROM chi_tiet_giay p \n" +
            "INNER JOIN giay g on p.id_giay = g.id_giay\n" +
            "INNER JOIN hang h on g.id_hang = h.id_hang\n" +
            "WHERE p.nam_bao_hanh > 0 AND p.nam_bao_hanh < YEAR(GETDATE())", nativeQuery = true)
    List<Object[]> dsHetBaoHanh();

    @Query(value = "select sum(so_luong) from chi_tiet_giay",nativeQuery = true)
    Integer getTongGiay();

    ChiTietGiay findByMaCTG(String ma);

    @Query(value = "select g.so_luong,g.trang_thai_mail from chi_tiet_giay g where g.trang_thai_mail=0",nativeQuery = true)
    List<Object[]> getSoLuongTon();

    @Query("SELECT c FROM ChiTietGiay c WHERE c.giay.idGiay = :giayId AND c.size.idSize = :sizeId AND c.mauSac.idMau = :mauSacId AND c.hinhAnh.idHinhAnh = :hinhAnhId")
    List<ChiTietGiay> findByGiayAndSizeAndMauSacAndHinhAnh(
            @Param("giayId") UUID giayId,
            @Param("sizeId") UUID sizeId,
            @Param("mauSacId") UUID mauSacId,
            @Param("hinhAnhId") UUID hinhAnhId
    );
}
