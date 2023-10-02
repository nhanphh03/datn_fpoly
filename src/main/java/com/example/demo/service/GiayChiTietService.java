package com.example.demo.service;

import com.example.demo.model.ChatLieu;
import com.example.demo.model.ChiTietGiay;

import java.util.List;
import java.util.UUID;

public interface GiayChiTietService {
    public List<ChiTietGiay> getAllChiTietGiay();

    public void save(ChiTietGiay chiTietGiay);

    public void deleteByIdChiTietGiay(UUID id);

    public ChiTietGiay getByIdChiTietGiay(UUID id);
}
