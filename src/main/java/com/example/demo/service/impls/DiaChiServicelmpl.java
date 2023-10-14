package com.example.demo.service.impls;

import com.example.demo.model.DiaChiKH;
import com.example.demo.repository.DiaChiRepsitory;
import com.example.demo.service.DiaChiKHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DiaChiServicelmpl implements DiaChiKHService {

    @Autowired
    private DiaChiRepsitory diaChiRepsitory;
    @Override
    public List<DiaChiKH> getAllDiaChiKH() {
        return diaChiRepsitory.findAll();
    }

    @Override
    public void save(DiaChiKH diaChiKH) {
        diaChiRepsitory.save(diaChiKH) ;
    }

    @Override
    public void deleteByIdDiaChiKH(UUID id) {
        diaChiRepsitory.deleteById(id);
    }

    @Override
    public DiaChiKH getByIdDiaChikh(UUID id) {
        return diaChiRepsitory.findById(id).orElse(null);
    }
}
