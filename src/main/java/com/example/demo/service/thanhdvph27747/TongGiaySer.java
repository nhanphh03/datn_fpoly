package com.example.demo.service.thanhdvph27747;

import com.example.demo.repository.thanhdvph27747.TongGiay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TongGiaySer {
    @Autowired
    private TongGiay tongGiay;

    public Integer getTongGiay(){
        return tongGiay.getTongGiay();
    }
}
