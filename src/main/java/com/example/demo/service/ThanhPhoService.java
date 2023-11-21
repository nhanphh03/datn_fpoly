package com.example.demo.service;

import com.example.demo.model.Province;

import java.util.List;

public interface ThanhPhoService {

    Province findByNameProvince(String nameProvince);
    List<Province> getAll();
}
