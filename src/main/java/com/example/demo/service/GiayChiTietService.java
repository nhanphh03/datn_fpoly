package com.example.demo.service;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GiayChiTietService {
    public List<ChiTietGiay> getAllChiTietGiay();

    public void save(ChiTietGiay chiTietGiay);

    public void update(ChiTietGiay chiTietGiay);

    public void deleteByIdChiTietGiay(UUID id);

    public ChiTietGiay getByIdChiTietGiay(UUID id);

    public List<ChiTietGiay> getCTGByGiay(Giay giay);

    List<ChiTietGiay> getCTGByGiayActive(Giay giay);

    List<ChiTietGiay> getCTGByGiaySoldOut(Giay giay);

    List<HinhAnh> listHinhAnhByGiay(Giay giay);
    //Double maxPriceGiay(Giay giay);

    public List<ChiTietGiay> fillterCTG(String searchTerm);

    public List<ChiTietGiay> fillterGCT(String searchTerm);

    List<Size> findDistinctSizeByGiay(Giay giay);

    List<MauSac> findDistinctMauSacByGiay(Giay giay);

}
