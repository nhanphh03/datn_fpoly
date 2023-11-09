package com.example.demo.repository.thanhdvph27747;

import com.example.demo.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TongKhachHang extends JpaRepository<KhachHang, UUID> {
    @Query(value = "select count(id_kh) from khach_hang" ,nativeQuery = true)
    Integer getTongKH();
}
