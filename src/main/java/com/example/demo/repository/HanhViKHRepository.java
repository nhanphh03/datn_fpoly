package com.example.demo.repository;

import com.example.demo.model.HanhViKhachHang;
import com.example.demo.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HanhViKHRepository extends JpaRepository<HanhViKhachHang, UUID> {

    HanhViKhachHang findByKhachHang(KhachHang khachHang);

}
