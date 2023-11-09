package com.example.demo.repository.thanhdvph27747;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.Giay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TongGiay extends JpaRepository<ChiTietGiay, UUID> {
    @Query(value = "select sum(so_luong) from chi_tiet_giay",nativeQuery = true)
    Integer getTongGiay();
}
