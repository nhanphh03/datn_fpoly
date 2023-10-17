package com.example.demo.repository;

import com.example.demo.model.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ChucVuRepsitory extends JpaRepository<ChucVu, UUID> {

    ChucVu findByMaCV(String maCV);
}
