package com.example.demo.repository;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.viewModel.CTGViewModel;
import com.example.demo.viewModel.GiayViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface GiayViewModelRepository extends JpaRepository<GiayViewModel, UUID> {

    @Query(value = "SELECT  ctg.id_giay, " +
            "g.ten_giay, " +
            "SUM(ctg.so_luong) AS \"sl_ton\", " +
            "ctg.gia_ban, " +
            "a.url1, " +
            "ms.ten_mau " +
            "FROM chi_tiet_giay ctg " +
            "JOIN giay g ON g.id_giay = ctg.id_giay " +
            "JOIN hinh_anh a ON a.id_hinh_anh = ctg.id_hinh_anh " +
            "JOIN mau_sac ms ON ctg.id_mau = ms.id_mau " +
            "WHERE g.trang_thai = 1 " +
            "AND ctg.trang_thai = 1 " +
            "AND g.ten_giay like  %?1% " +
            "GROUP BY ctg.id_giay, g.ten_giay, a.url1, ctg.gia_ban, ms.ten_mau", nativeQuery = true)
    List<GiayViewModel> searchByTenGiay(String keyword);
}
