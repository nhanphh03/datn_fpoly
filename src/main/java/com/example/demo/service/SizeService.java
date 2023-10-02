package com.example.demo.service;

import com.example.demo.model.ChatLieu;
import com.example.demo.model.Size;

import java.util.List;
import java.util.UUID;

public interface SizeService {
    public List<Size> getAllSize();

    public void save(Size size);

    public void deleteByIdSize(UUID id);

    public Size getByIdSize(UUID id);
}
