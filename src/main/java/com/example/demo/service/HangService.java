package com.example.demo.service;

import com.example.demo.model.ChatLieu;
import com.example.demo.model.Hang;

import java.util.List;
import java.util.UUID;

public interface HangService {
    public List<Hang> getALlHang();

    public void save(Hang hang);

    public void deleteByIdHang(UUID id);

    public Hang getByIdHang(UUID id);
}
