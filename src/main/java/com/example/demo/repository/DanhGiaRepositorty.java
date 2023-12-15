package com.example.demo.repository;

import com.example.demo.model.DanhGiaKhachHang;
import com.example.demo.model.Giay;
import com.example.demo.model.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface DanhGiaRepositorty extends JpaRepository<DanhGiaKhachHang, UUID> {

    List<DanhGiaKhachHang> findByGiay(Giay giay);

    DanhGiaKhachHang findByHoaDon(HoaDon hoaDon);
}
