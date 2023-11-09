package com.example.demo.repository;

import com.example.demo.model.LoaiKhuyenMai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoaiKhuyenMaiRepository extends JpaRepository<LoaiKhuyenMai, UUID> {

    LoaiKhuyenMai findByMaLKM(String maLKM);

}
