package com.example.demo.repository;

import com.example.demo.viewModel.CTGViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CTGViewModelRepository extends JpaRepository<CTGViewModel, UUID> {

    @Query(value = "SELECT\n" +
            "    ctg.id_giay,\n" +
            "    MIN(ctg.gia_ban) AS min_price,\n" +
            "    g.ten_giay,\n" +
            "    SUM(ctg.so_luong) AS sl_ton,\n" +
            "    a.url1,\n" +
            "    COALESCE(SUM(cthd.so_luong), 0) AS so_Luong_Da_Ban\n" +
            "FROM\n" +
            "    chi_tiet_giay ctg\n" +
            "JOIN\n" +
            "    giay g ON g.id_giay = ctg.id_giay\n" +
            "JOIN\n" +
            "    hinh_anh a ON a.id_hinh_anh = ctg.id_hinh_anh\n" +
            "LEFT JOIN\n" +
            "    hoa_don_chi_tiet cthd ON cthd.id_ctg = ctg.id_chi_tiet_giay\n" +
            "WHERE\n" +
            "    ctg.id_giay IS NOT NULL\n" +
            "    AND ctg.id_hinh_anh IS NOT NULL\n" +
            "\tAND g.trang_thai Like 1\n" +
            "\tAND ctg.trang_thai like 1\n" +
            "GROUP BY\n" +
            "    ctg.id_giay,\n" +
            "    g.ten_giay,\n" +
            "    a.url1;", nativeQuery = true)
    List<CTGViewModel> getAll();


    @Query(value = "SELECT\n" +
            "    ctg.id_giay,\n" +
            "    MIN(ctg.gia_ban) AS min_price,\n" +
            "    g.ten_giay,\n" +
            "    SUM(ctg.so_luong) AS sl_ton,\n" +
            "    a.url1,\n" +
            "    COALESCE(SUM(cthd.so_luong), 0) AS so_Luong_Da_Ban\n" +
            "FROM\n" +
            "    chi_tiet_giay ctg\n" +
            "JOIN\n" +
            "    giay g ON g.id_giay = ctg.id_giay\n" +
            "JOIN\n" +
            "    hinh_anh a ON a.id_hinh_anh = ctg.id_hinh_anh\n" +
            "LEFT JOIN\n" +
            "    hoa_don_chi_tiet cthd ON cthd.id_ctg = ctg.id_chi_tiet_giay\n" +
            "WHERE\n" +
            "    ctg.id_giay IS NOT NULL\n" +
            "    AND ctg.id_hinh_anh IS NOT NULL\n" +
            "\tAND g.trang_thai Like 1\n" +
            "\tAND ctg.trang_thai like 1\n" +
            "GROUP BY\n" +
            "    ctg.id_giay,\n" +
            "    g.ten_giay,\n" +
            "    a.url1;", nativeQuery = true)
    Page<CTGViewModel> getAllPageable(Pageable pageable);


    @Query(value = "SELECT\n" +
            "    ctg.id_giay,\n" +
            "    MIN(ctg.gia_ban) AS min_price,\n" +
            "    g.ten_giay,\n" +
            "    SUM(ctg.so_luong) AS sl_ton,\n" +
            "    a.url1,\n" +
            "    COALESCE(SUM(cthd.so_luong), 0) AS so_Luong_Da_Ban\n" +
            "FROM\n" +
            "    chi_tiet_giay ctg\n" +
            "JOIN\n" +
            "    giay g ON g.id_giay = ctg.id_giay\n" +
            "JOIN\n" +
            "    hinh_anh a ON a.id_hinh_anh = ctg.id_hinh_anh\n" +
            "LEFT JOIN\n" +
            "    hoa_don_chi_tiet cthd ON cthd.id_ctg = ctg.id_chi_tiet_giay\n" +
            "WHERE\n" +
            "    ctg.id_giay IS NOT NULL\n" +
            "    AND ctg.id_hinh_anh IS NOT NULL\n" +
            "\tAND g.trang_thai Like 1\n" +
            "\tAND ctg.trang_thai like 0\n" +
            "GROUP BY\n" +
            "    ctg.id_giay,\n" +
            "    g.ten_giay,\n" +
            "    a.url1;", nativeQuery = true)
    List<CTGViewModel> getAllInActive();

    @Query(value = "SELECT\n" +
            "    ctg.id_giay,\n" +
            "    MIN(ctg.gia_ban) AS min_price,\n" +
            "    g.ten_giay,\n" +
            "    SUM(ctg.so_luong) AS sl_ton,\n" +
            "    a.url1,\n" +
            "    COALESCE(SUM(cthd.so_luong), 0) AS so_Luong_Da_Ban\n" +
            "FROM\n" +
            "    chi_tiet_giay ctg\n" +
            "JOIN\n" +
            "    giay g ON g.id_giay = ctg.id_giay\n" +
            "JOIN\n" +
            "    hinh_anh a ON a.id_hinh_anh = ctg.id_hinh_anh\n" +
            "LEFT JOIN\n" +
            "    hoa_don_chi_tiet cthd ON cthd.id_ctg = ctg.id_chi_tiet_giay\n" +
            "WHERE\n" +
            "    ctg.id_giay IS NOT NULL\n" +
            "    AND ctg.id_hinh_anh IS NOT NULL\n" +
            "\tAND g.trang_thai Like 1\n" +
            "\tAND ctg.trang_thai like 2\n" +
            "GROUP BY\n" +
            "    ctg.id_giay,\n" +
            "    g.ten_giay,\n" +
            "    a.url1;", nativeQuery = true)
    List<CTGViewModel> getAllOutOfStock();

    @Query(value = "SELECT\n" +
            "    ctg.id_giay,\n" +
            "    MIN(ctg.gia_ban) AS min_price,\n" +
            "    g.ten_giay,\n" +
            "    SUM(ctg.so_luong) AS sl_ton,\n" +
            "    a.url1,\n" +
            "    COALESCE(SUM(cthd.so_luong), 0) AS so_Luong_Da_Ban\n" +
            "FROM\n" +
            "    chi_tiet_giay ctg\n" +
            "JOIN\n" +
            "    giay g ON g.id_giay = ctg.id_giay\n" +
            "JOIN\n" +
            "    hinh_anh a ON a.id_hinh_anh = ctg.id_hinh_anh\n" +
            "LEFT JOIN\n" +
            "    hoa_don_chi_tiet cthd ON cthd.id_ctg = ctg.id_chi_tiet_giay\n" +
            "WHERE\n" +
            "    ctg.id_giay like ?1 \n" +
            "    AND ctg.id_hinh_anh IS NOT NULL\n" +
            "\tAND g.trang_thai Like 1\n" +
            "\tAND ctg.trang_thai like 1\n" +
            "GROUP BY\n" +
            "    ctg.id_giay,\n" +
            "    g.ten_giay,\n" +
            "    a.url1;", nativeQuery = true)
    CTGViewModel findByGiay(UUID idGiay);
}
