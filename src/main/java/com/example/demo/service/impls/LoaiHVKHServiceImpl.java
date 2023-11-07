package com.example.demo.service.impls;

import com.example.demo.model.LoaiHanhVi;
import com.example.demo.repository.LoaiHVRepository;
import com.example.demo.service.LoaiHVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoaiHVKHServiceImpl implements LoaiHVService {

    @Autowired
    private LoaiHVRepository loaiHVRepository;

    @Override
    public LoaiHanhVi findByMaLHVCDN(String maloaiHV) {
        return loaiHVRepository.findByMaLoaiHVAndTtLogin(maloaiHV, false);
    }

    @Override
    public LoaiHanhVi findByMaLHVDDN(String maloaiHV) {
        return loaiHVRepository.findByMaLoaiHVAndTtLogin(maloaiHV,true);
    }
}
