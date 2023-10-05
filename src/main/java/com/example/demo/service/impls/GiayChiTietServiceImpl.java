package com.example.demo.service.impls;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.Giay;
import com.example.demo.repository.GiayChiTietRepository;
import com.example.demo.repository.GiayRepository;
import com.example.demo.service.GiayChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GiayChiTietServiceImpl implements GiayChiTietService {
    @Autowired
    private GiayChiTietRepository giayChiTietRepository;
    @Autowired
    private GiayRepository giayRepository;

    @Override
    public List<ChiTietGiay> getAllChiTietGiay() {
        return giayChiTietRepository.findAll();
    }

    @Override
    public void save(ChiTietGiay chiTietGiay) {
        giayChiTietRepository.save(chiTietGiay);
    }

    @Override
    public void deleteByIdChiTietGiay(UUID id) {
        giayChiTietRepository.deleteById(id);
    }

    @Override
    public ChiTietGiay getByIdChiTietGiay(UUID id) {
        return giayChiTietRepository.findById(id).orElse(null);
    }


}
