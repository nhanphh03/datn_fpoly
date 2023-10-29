package com.example.demo.repository;

import com.example.demo.model.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatLieuRepository extends JpaRepository<ChatLieu, UUID> {
    List<ChatLieu> findByMaChatLieuOrTenChatLieu(String maCL, String tenCL);

    ChatLieu findByTenChatLieu(String name);

    List<ChatLieu> findAllByOrderByTgThemDesc();

    ChatLieu findByMaChatLieu(String maChatLieu);
}
