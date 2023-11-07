package com.example.demo.repository;

import com.example.demo.model.HoaDonChiTiet;
import com.example.demo.viewModel.GiayViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface GiayViewModelRepository extends JpaRepository<GiayViewModel, UUID> {
    @Query("SELECT NEW com.example.demo.viewModel.GiayViewModel(" +
            " g.idGiay, g.tenGiay, SUM(ctg.soLuong),min( ctg.giaBan), a.url1, ms.tenMau) " +
            "FROM Giay g " +
            "JOIN ChiTietGiay ctg ON g.idGiay = ctg.giay.idGiay " +
            "JOIN HinhAnh a ON a.idHinhAnh = ctg.hinhAnh.idHinhAnh " +
            "JOIN MauSac ms ON ctg.mauSac.idMau = ms.idMau " +
            "WHERE g.trangThai = 1 " +
            "AND ctg.trangThai = 1 " +
            "AND g.tenGiay LIKE %:keyword% " +
            "GROUP BY g.idGiay, g.tenGiay, a.url1, ms.tenMau")
    List<GiayViewModel> searchByTenGiay(@Param("keyword") String keyword);

    @Query(value = "SELECT ctg from ChiTietGiay ctg where ctg.giay.idGiay = ?1")
    GiayViewModel findByIdGiay(UUID id);
}

