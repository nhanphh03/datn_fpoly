package com.example.demo.service.impls;

import com.example.demo.model.ChatLieu;
import com.example.demo.repository.ChatLieuRepository;
import com.example.demo.service.ChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChatLieuServiceImpl implements ChatLieuService {
    @Autowired
    private ChatLieuRepository chatLieuRepository;

    @Override
    public List<ChatLieu> getAllChatLieu() {
        return chatLieuRepository.findAll();
    }

    @Override
    public void save(ChatLieu chatLieu) {
        chatLieuRepository.save(chatLieu);
    }

    @Override
    public void deleteByIdChatLieu(UUID id) {
        chatLieuRepository.deleteById(id);
    }

    @Override
    public ChatLieu getByIdChatLieu(UUID id) {
        return chatLieuRepository.findById(id).orElse(null);
    }
}
