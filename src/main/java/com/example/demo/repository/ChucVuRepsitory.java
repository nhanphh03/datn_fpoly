package com.example.demo.repository;

import com.example.demo.model.ChucVu;
import com.example.demo.model.Hang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ChucVuRepsitory extends JpaRepository<ChucVu, UUID> {
    List<ChucVu> getByTrangThai(int trangThai);

<<<<<<< HEAD
    List<ChucVu> findByMaCVOrTenCV(String maCV, String tenCV);
=======
    ChucVu findByMaCV(String maCV);
>>>>>>> 249b67ef71ce6d1623fe035c23e19ee9c767c8a0
}
