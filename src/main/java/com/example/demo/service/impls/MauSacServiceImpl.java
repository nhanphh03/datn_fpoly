package com.example.demo.service.impls;

import com.example.demo.model.MauSac;
import com.example.demo.repository.MauSacRepository;
import com.example.demo.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MauSacServiceImpl implements MauSacService {
    @Autowired
    private MauSacRepository mauSacRepository;

    @Override
    public List<MauSac> getALlMauSac() {
        return mauSacRepository.findAll();
    }

    @Override
    public void save(MauSac mauSac) {
        mauSacRepository.save(mauSac);
    }

    @Override
    public void deleteByIdMauSac(UUID id) {
        mauSacRepository.deleteById(id);
    }

    @Override
    public MauSac getByIdMauSac(UUID id) {
        return mauSacRepository.findById(id).orElse(null);
    }
}
