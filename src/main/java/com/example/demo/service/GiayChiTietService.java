package com.example.demo.service;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.Giay;
import com.example.demo.model.HinhAnh;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GiayChiTietService {
    public List<ChiTietGiay> getAllChiTietGiay();

    public void save(ChiTietGiay chiTietGiay);

    public void deleteByIdChiTietGiay(UUID id);

    public ChiTietGiay getByIdChiTietGiay(UUID id);

    public List<ChiTietGiay> getCTGByGiay(Giay giay);

    List<ChiTietGiay> getCTGByGiayActive(Giay giay);

    List<HinhAnh> listHinhAnhByGiay(Giay giay);
    //Double maxPriceGiay(Giay giay);

    public List<ChiTietGiay> fillterCTG(String searchTerm);

    public List<ChiTietGiay> fillterGCT(String searchTerm);


}
