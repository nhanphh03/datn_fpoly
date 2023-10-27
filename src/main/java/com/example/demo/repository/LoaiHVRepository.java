package com.example.demo.repository;

import com.example.demo.model.LoaiHanhVi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoaiHVRepository extends JpaRepository<LoaiHanhVi, UUID> {

    LoaiHanhVi findByMaLoaiHVAndTtLogin(String maLoaiHV, boolean trangThai);
}
