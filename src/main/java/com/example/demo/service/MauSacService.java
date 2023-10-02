package com.example.demo.service;

import com.example.demo.model.ChatLieu;
import com.example.demo.model.MauSac;

import java.util.List;
import java.util.UUID;

public interface MauSacService {
    public List<MauSac> getALlMauSac();

    public void save(MauSac mauSac);

    public void deleteByIdMauSac(UUID id);

    public MauSac getByIdMauSac(UUID id);
}
