package com.example.demo.service.thanhdvph27747;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.repository.thanhdvph27747.SPBanChay;
import com.example.demo.repository.thanhdvph27747.getTopSP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SPBanChaySer {
    @Autowired
    private SPBanChay spBanChay;

    public List<ChiTietGiay> getTop5(){
        return spBanChay.getTop5();
    }

}
