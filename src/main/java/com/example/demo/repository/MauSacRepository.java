package com.example.demo.repository;

import com.example.demo.model.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, UUID> {

    List<MauSac> findByTrangThai(int trangThai);

    List<MauSac> findByMaMauOrTenMau(String maMau, String tenMau);

    MauSac findByTenMau(String name);

    List<MauSac> findAllByOrderByTgThemDesc();

    MauSac findByMa(String maMau);
}
