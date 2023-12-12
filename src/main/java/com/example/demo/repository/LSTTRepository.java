package com.example.demo.repository;


import com.example.demo.model.LichSuThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LSTTRepository extends JpaRepository<LichSuThanhToan, UUID> {
}
