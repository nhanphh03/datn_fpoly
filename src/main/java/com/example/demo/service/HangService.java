package com.example.demo.service;

import com.example.demo.model.Hang;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public interface HangService {
    public List<Hang> getALlHang();

    public void save(Hang hang);

    public void deleteByIdHang(UUID id);

    public Hang getByIdHang(UUID id);

    public List<Hang> getAllActive();

    public List<Hang> fillterHang(String maHang, String tenHang);

    public void importDataFromExcel(InputStream excelFile);

}
