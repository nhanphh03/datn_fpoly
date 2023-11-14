package com.example.demo.repository;

import com.example.demo.model.KhuyenMai;
import com.example.demo.model.LoaiKhuyenMai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai, UUID> {

    List<KhuyenMai> findByTrangThai(int trangThai);
    List<KhuyenMai> findByLoaiKhuyenMai(LoaiKhuyenMai loaiKhuyenMai);
    List<KhuyenMai> findByLoaiKhuyenMaiAndTrangThai(LoaiKhuyenMai loaiKhuyenMai, int trangThai);
    KhuyenMai findByMaKM(String maKM);


}
