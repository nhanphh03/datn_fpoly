package com.example.demo.repository;

import com.example.demo.model.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface HinhAnhRepository extends JpaRepository<HinhAnh, UUID> {
    HinhAnh findByMaAnh(String name);
}
