package com.example.demo.service;

import com.example.demo.model.LichSuThanhToan;

import java.util.List;

public interface LSThanhToanService {

    void addLSTT(LichSuThanhToan lichSuThanhToan);

    List<LichSuThanhToan> getAllLSTT();
}
