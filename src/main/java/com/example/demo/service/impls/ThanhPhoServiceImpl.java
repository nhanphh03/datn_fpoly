package com.example.demo.service.impls;

import com.example.demo.model.Province;
import com.example.demo.repository.ThanhPhoRepository;
import com.example.demo.service.ThanhPhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThanhPhoServiceImpl implements ThanhPhoService {

    @Autowired
    private ThanhPhoRepository thanhPhoRepository;

    @Override
    public Province findByNameProvince(String nameProvince) {
        return thanhPhoRepository.findByNameProvince(nameProvince);
    }

    @Override
    public List<Province> getAll() {
        return thanhPhoRepository.findAll();
    }
}
