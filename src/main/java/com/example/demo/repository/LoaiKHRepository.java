package com.example.demo.repository;


import com.example.demo.model.LoaiKhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoaiKHRepository extends JpaRepository<LoaiKhachHang, UUID> {

    LoaiKhachHang findByTenLKH(String nameLKH);

}
