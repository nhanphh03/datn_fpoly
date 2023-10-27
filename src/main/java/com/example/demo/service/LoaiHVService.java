package com.example.demo.service;

import com.example.demo.model.LoaiHanhVi;

public interface LoaiHVService {

    LoaiHanhVi findByMaLHVCDN(String maloaiHV);

    LoaiHanhVi findByMaLHVDDN(String maloaiHV);
}
