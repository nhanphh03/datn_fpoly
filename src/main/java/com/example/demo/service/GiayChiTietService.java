package com.example.demo.service;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.Query;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GiayChiTietService {
    public List<ChiTietGiay> getAllChiTietGiay();

    public List<ChiTietGiay> getTop4ChiTietGiay();

    public void save(ChiTietGiay chiTietGiay);

    public void update(ChiTietGiay chiTietGiay);

    public void deleteByIdChiTietGiay(UUID id);

    public ChiTietGiay getByIdChiTietGiay(UUID id);

    public List<ChiTietGiay> getCTGByGiay(Giay giay);

    List<ChiTietGiay> getCTGByGiayActive(Giay giay);

    List<ChiTietGiay> getCTGByGiaySoldOut(Giay giay);

    HinhAnh hinhAnhByGiayAndMau(Giay giay, MauSac mauSac);
    //Double maxPriceGiay(Giay giay);

    public List<ChiTietGiay> fillterCTG(String searchTerm);

    public List<ChiTietGiay> fillterGCT(String searchTerm);

    public void importDataFromExcel(InputStream excelFile);

    public List<ChiTietGiay> findByGiay(Giay giay);

    public List<ChiTietGiay> findByMauSac(MauSac mauSac);

    public List<ChiTietGiay> findBySize(Size size);

    List<Size> findDistinctSizeByGiayAndMauSac(Giay giay, MauSac mauSac);

    List<ChiTietGiay> findByMauSacAndGiay(MauSac mauSac, Giay giay, int trangThai);

    List<MauSac> findDistinctMauSacByGiay(Giay giay);

    void updatePriceCTGGHCT(ChiTietGiay chiTietGiay);

}
