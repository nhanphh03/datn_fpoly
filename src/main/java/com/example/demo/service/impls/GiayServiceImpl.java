package com.example.demo.service.impls;

import com.example.demo.model.Giay;
import com.example.demo.repository.GiayRepository;
import com.example.demo.service.GiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GiayServiceImpl implements GiayService {
    @Autowired
    private GiayRepository giayRepository;

    @Override
    public List<Giay> getAllGiay() {
        return giayRepository.findAll();
    }

    @Override
    public void save(Giay giay) {
        giayRepository.save(giay);
    }

    @Override
    public void deleteByIdGiay(UUID id) {
        giayRepository.deleteById(id);
    }

    @Override
    public Giay getByIdGiay(UUID id) {
        return giayRepository.findById(id).orElse(null);
    }

    @Override
    public Giay getByName(String name) {
        return giayRepository.findByTenGiay(name);
    }

    @Override
    public List<Giay> fillterGiay(String searchTerm) {
        if ("Mã Giày".equals(searchTerm) && "Tên Giày".equals(searchTerm) && "Hãng".equals(searchTerm) && "Chất Liệu".equals(searchTerm)) {
            return giayRepository.findAll();
        }
        return giayRepository.customSearch(searchTerm);
    }
}
