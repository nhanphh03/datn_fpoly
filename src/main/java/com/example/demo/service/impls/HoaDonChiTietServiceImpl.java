package com.example.demo.service.impls;

import com.example.demo.model.HoaDonChiTiet;
import com.example.demo.repository.HoaDonChiTietRepository;
import com.example.demo.repository.HoaDonRepository;
import com.example.demo.service.HoaDonChiTietService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Override
    public HoaDonChiTiet getOne(UUID idHoaDon, UUID idChiTietGiay) {
        return hoaDonChiTietRepository.findByIdHoaDonAndIdChiTietGiay(idHoaDon, idChiTietGiay);
    }

    @Override
    public void add(HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public List<HoaDonChiTiet> findByIdHoaDon(UUID id) {
        return hoaDonChiTietRepository.findByIdHoaDon(id);
    }
}
