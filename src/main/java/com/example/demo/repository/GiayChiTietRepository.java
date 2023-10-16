package com.example.demo.repository;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.HinhAnh;
import com.example.demo.viewModel.CTGViewModel;
import com.example.demo.model.Giay;
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

    @Query(value = "SELECT DISTINCT ctg.hinhAnh FROM ChiTietGiay ctg WHERE ctg.giay = ?1")
    List<HinhAnh> findDistinctByGiay(Giay giay);

    @Query("SELECT g FROM ChiTietGiay g WHERE g.size.soSize = :searchTerm OR g.mauSac.tenMau = :searchTerm")
    List<ChiTietGiay> customSearchCTG(@Param("searchTerm") String searchTerm);

    @Query("SELECT g FROM ChiTietGiay g WHERE g.size.soSize = :searchTerm OR g.mauSac.tenMau = :searchTerm OR g.giay.tenGiay = :searchTerm")
    List<ChiTietGiay> customSearchGCT(@Param("searchTerm") String searchTerm);
}
