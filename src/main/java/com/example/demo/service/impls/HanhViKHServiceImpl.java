package com.example.demo.service.impls;

import com.example.demo.model.HanhViKhachHang;
import com.example.demo.model.KhachHang;
import com.example.demo.repository.HanhViKHRepository;
import com.example.demo.service.HanhViKHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HanhViKHServiceImpl implements HanhViKHService {

    @Autowired
    private HanhViKHRepository hanhViKHRepository;

    @Override
    public void addNewHanhViKH(HanhViKhachHang hanhViKhachHang) {
         hanhViKHRepository.save(hanhViKhachHang);
    }

    @Override
    public HanhViKhachHang checkByKH(KhachHang khachHang) {
        return hanhViKHRepository.findByKhachHang(khachHang);
    }
}
