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

    @Query("SELECT NEW com.example.demo.viewModel.CTGViewModel(" +
            "ctg.giay.idGiay, ctg.mauSac.idMau, MIN(ctg.giaBan), g.tenGiay, SUM(ctg.soLuong), a.url1 , COALESCE(SUM(cthd.soLuong), 0))" +
            "FROM ChiTietGiay ctg " +
            "JOIN Giay g ON g.idGiay = ctg.giay.idGiay\n" +
            "JOIN HinhAnh a ON a.idHinhAnh = ctg.hinhAnh.idHinhAnh\n" +
            "JOIN MauSac ms ON ms.idMau = ctg.mauSac.idMau\n" +
            "LEFT JOIN HoaDonChiTiet cthd ON cthd.chiTietGiay.idCTG = ctg.idCTG " +
            "WHERE ctg.giay.idGiay IS NOT NULL " +
            "AND ctg.hinhAnh.idHinhAnh IS NOT NULL " +
            "AND g.trangThai = 1 " +
            "AND ctg.trangThai = 1 " +
            "GROUP BY ctg.giay.idGiay, ctg.mauSac.idMau, g.tenGiay, a.url1")
    List<CTGViewModel> getAll();

    @Query("SELECT NEW com.example.demo.viewModel.CTGViewModel(" +
            "ctg.giay.idGiay, ctg.mauSac.idMau, MIN(ctg.giaBan), g.tenGiay, SUM(ctg.soLuong), a.url1 , COALESCE(SUM(cthd.soLuong), 0))" +
            "FROM ChiTietGiay ctg " +
            "JOIN Giay g ON g.idGiay = ctg.giay.idGiay\n" +
            "JOIN HinhAnh a ON a.idHinhAnh = ctg.hinhAnh.idHinhAnh\n" +
            "JOIN MauSac ms ON ms.idMau = ctg.mauSac.idMau\n" +
            "LEFT JOIN HoaDonChiTiet cthd ON cthd.chiTietGiay.idCTG = ctg.idCTG " +
            "WHERE ctg.giay.idGiay IS NOT NULL " +
            "AND ctg.hinhAnh.idHinhAnh IS NOT NULL " +
            "AND g.trangThai = 1 " +
            "AND ctg.trangThai = 1 " +
            "GROUP BY ctg.giay.idGiay, ctg.mauSac.idMau, g.tenGiay, a.url1")
    Page<CTGViewModel> getAllPageable(Pageable pageable);

    @Query("SELECT NEW com.example.demo.viewModel.CTGViewModel(" +
            "ctg.giay.idGiay, MIN(ctg.giaBan), g.tenGiay, SUM(ctg.soLuong), a.url1 , COALESCE(SUM(cthd.soLuong), 0))" +
            "FROM ChiTietGiay ctg " +
            "JOIN Giay g ON g.idGiay = ctg.giay.idGiay\n" +
            "JOIN HinhAnh a ON a.idHinhAnh = ctg.hinhAnh.idHinhAnh\n" +
            "LEFT JOIN HoaDonChiTiet cthd ON cthd.chiTietGiay.idCTG = ctg.idCTG " +
            "WHERE ctg.giay.idGiay IS NOT NULL " +
            "AND ctg.hinhAnh.idHinhAnh IS NOT NULL " +
            "AND g.trangThai = 1 " +
            "AND ctg.trangThai = 0 " +
            "GROUP BY ctg.giay.idGiay, g.tenGiay, a.url1")
    List<CTGViewModel> getAllInActive();

    @Query("SELECT NEW com.example.demo.viewModel.CTGViewModel(" +
            "ctg.giay.idGiay, ctg.mauSac.idMau, MIN(ctg.giaBan), g.tenGiay, SUM(ctg.soLuong), a.url1 , COALESCE(SUM(cthd.soLuong), 0))" +
            "FROM ChiTietGiay ctg " +
            "JOIN Giay g ON g.idGiay = ctg.giay.idGiay\n" +
            "JOIN HinhAnh a ON a.idHinhAnh = ctg.hinhAnh.idHinhAnh\n" +
            "JOIN MauSac ms ON ms.idMau = ctg.mauSac.idMau\n" +
            "LEFT JOIN HoaDonChiTiet cthd ON cthd.chiTietGiay.idCTG = ctg.idCTG " +
            "WHERE ctg.giay.idGiay IS NOT NULL " +
            "AND ctg.hinhAnh.idHinhAnh IS NOT NULL " +
            "AND g.trangThai = 1 " +
            "AND ctg.trangThai = 2 " +
            "GROUP BY ctg.giay.idGiay, ctg.mauSac.idMau, g.tenGiay, a.url1")
    List<CTGViewModel> getAllOutOfStock();

    @Query(value = "SELECT NEW com.example.demo.viewModel.CTGViewModel(" +
            "ctg.giay.idGiay, ctg.mauSac.idMau, MIN(ctg.giaBan), g.tenGiay, SUM(ctg.soLuong), a.url1 , COALESCE(SUM(cthd.soLuong), 0))" +
            "FROM ChiTietGiay ctg " +
            "JOIN Giay g ON g.idGiay = ctg.giay.idGiay\n" +
            "JOIN HinhAnh a ON a.idHinhAnh = ctg.hinhAnh.idHinhAnh\n" +
            "JOIN MauSac ms ON ms.idMau = ctg.mauSac.idMau\n" +
            "LEFT JOIN HoaDonChiTiet cthd ON cthd.chiTietGiay.idCTG = ctg.idCTG " +
            "WHERE ctg.giay.idGiay like ?1 " +
            "AND ctg.mauSac.idMau like ?2 " +
            "AND ctg.hinhAnh.idHinhAnh IS NOT NULL " +
            "AND g.trangThai = 1 " +
            "AND ctg.trangThai = 1 " +
            "GROUP BY ctg.giay.idGiay, ctg.mauSac.idMau, g.tenGiay, a.url1")
    CTGViewModel findByGiay(UUID idGiay, UUID idMau);

    @Query("SELECT NEW com.example.demo.viewModel.CTGViewModel(" +
            "ctg.giay.idGiay, ctg.mauSac.idMau, MIN(ctg.giaBan), g.tenGiay, SUM(ctg.soLuong), a.url1 , COALESCE(SUM(cthd.soLuong), 0))" +
            "FROM ChiTietGiay ctg " +
            "JOIN Giay g ON g.idGiay = ctg.giay.idGiay\n" +
            "JOIN HinhAnh a ON a.idHinhAnh = ctg.hinhAnh.idHinhAnh\n" +
            "JOIN MauSac ms ON ms.idMau = ctg.mauSac.idMau\n" +
            "LEFT JOIN HoaDonChiTiet cthd ON cthd.chiTietGiay.idCTG = ctg.idCTG " +
            "WHERE ctg.giay.idGiay IS NOT NULL " +
            "AND ctg.hinhAnh.idHinhAnh IS NOT NULL " +
            "AND g.trangThai = 1 " +
            "AND ctg.trangThai = 1 " +
            "AND g.hang.idHang = ?1 " +
            "GROUP BY ctg.giay.idGiay, ctg.mauSac.idMau, g.tenGiay, a.url1")
    List<CTGViewModel> findByHang(UUID idHang);

    @Query("SELECT NEW com.example.demo.viewModel.CTGViewModel(" +
            "ctg.giay.idGiay, MIN(ctg.giaBan) AS minPrice, g.tenGiay, SUM(ctg.soLuong) , a.url1 ,COALESCE(SUM(cthd.soLuong), 0))" +
            "FROM ChiTietGiay ctg " +
            "JOIN Giay g ON g.idGiay = ctg.giay.idGiay\n" +
            "JOIN HinhAnh a ON a.idHinhAnh = ctg.hinhAnh.idHinhAnh\n" +
            "JOIN MauSac ms ON ms.idMau = ctg.mauSac.idMau\n" +
            "LEFT JOIN HoaDonChiTiet cthd ON cthd.chiTietGiay.idCTG = ctg.idCTG " +
            "WHERE ctg.giay.idGiay IS NOT NULL " +
            "AND ctg.hinhAnh.idHinhAnh IS NOT NULL " +
            "AND g.trangThai = 1 " +
            "AND ctg.trangThai = 1 " +
            "AND g.hang.idHang = ?1 " +
            "GROUP BY ctg.giay.idGiay, ctg.mauSac.idMau, g.tenGiay, a.url1 "+
            "ORDER BY minPrice")
    Page<CTGViewModel> listCTGVMHTL(Pageable pageable);

    @Query("SELECT NEW com.example.demo.viewModel.CTGViewModel(" +
            "ctg.giay.idGiay, MIN(ctg.giaBan) AS minPrice, g.tenGiay, SUM(ctg.soLuong), a.url1 ,COALESCE(SUM(cthd.soLuong), 0))" +
            "FROM ChiTietGiay ctg " +
            "JOIN Giay g ON g.idGiay = ctg.giay.idGiay\n" +
            "JOIN HinhAnh a ON a.idHinhAnh = ctg.hinhAnh.idHinhAnh\n" +
            "JOIN MauSac ms ON ms.idMau = ctg.mauSac.idMau\n" +
            "LEFT JOIN HoaDonChiTiet cthd ON cthd.chiTietGiay.idCTG = ctg.idCTG " +
            "WHERE ctg.giay.idGiay IS NOT NULL " +
            "AND ctg.hinhAnh.idHinhAnh IS NOT NULL " +
            "AND g.trangThai = 1 " +
            "AND ctg.trangThai = 1 " +
            "AND g.hang.idHang = ?1 " +
            "GROUP BY ctg.giay.idGiay, ctg.mauSac.idMau, g.tenGiay, a.url1 "+
            "ORDER BY minPrice")
    Page<CTGViewModel> listCTGVMLTH(Pageable pageable);
}
