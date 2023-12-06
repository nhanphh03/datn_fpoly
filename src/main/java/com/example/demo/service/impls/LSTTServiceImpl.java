package com.example.demo.service.impls;

import com.example.demo.model.LichSuThanhToan;
import com.example.demo.repository.LSTTRepository;
import com.example.demo.service.LSThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LSTTServiceImpl implements LSThanhToanService {

    @Autowired
    private LSTTRepository lsttRepository;

    @Override
    public void addLSTT(LichSuThanhToan lichSuThanhToan) {
        lsttRepository.save(lichSuThanhToan);
    }

    @Override
    public List<LichSuThanhToan> getAllLSTT() {
        return lsttRepository.findAll();
    }
}
