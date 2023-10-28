package com.example.demo.repository;

import com.example.demo.model.*;
import com.example.demo.viewModel.CTGViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    List<ChiTietGiay> findAllByOrderByTgThemDesc();
}
