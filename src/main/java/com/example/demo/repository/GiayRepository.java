package com.example.demo.repository;

import com.example.demo.model.ChatLieu;
import com.example.demo.model.Giay;
import com.example.demo.model.Hang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GiayRepository extends JpaRepository<Giay, UUID> {
    Giay findByTenGiay(String tenGiay);

    @Query("SELECT g FROM Giay g WHERE g.maGiay = :searchTerm OR g.tenGiay = :searchTerm OR g.hang.tenHang = :searchTerm OR g.chatLieu.tenChatLieu = :searchTerm")
    List<Giay> customSearch(@Param("searchTerm") String searchTerm);

    List<Giay> findByHang(Hang hang);

    List<Giay> findByChatLieu(ChatLieu chatLieu);

    List<Giay> findByTrangThai(int trangThai);

    List<Giay> findAllByOrderByTgThemDesc();

    Giay findByMaGiay(String maGiay);
}
