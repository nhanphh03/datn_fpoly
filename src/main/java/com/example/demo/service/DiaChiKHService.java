package com.example.demo.service;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.DiaChiKH;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface DiaChiKHService {
    public List<DiaChiKH> getAllDiaChiKH();

    public void save(DiaChiKH diaChiKH);

    public void deleteByIdDiaChiKH(UUID id);

    public DiaChiKH getByIdDiaChikh(UUID id);



}
