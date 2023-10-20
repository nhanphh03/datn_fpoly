package com.example.demo.service;

import com.example.demo.model.ChucVu;
import com.example.demo.model.Hang;
import com.example.demo.model.HinhAnh;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface ChucVuService {
    public List<ChucVu> getAllChucVu();

    public void save(ChucVu chucVu);

    public void deleteByIdChucVu(UUID id);

    public ChucVu getByIdChucVu(UUID id);
    public List<ChucVu> getAllActive();

    public List<ChucVu> fillterChucVu(String maCV, String tenCV);

    ChucVu findByMaCV(String maCV);


}
