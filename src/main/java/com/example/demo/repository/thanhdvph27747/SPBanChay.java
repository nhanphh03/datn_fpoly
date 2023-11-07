package com.example.demo.repository.thanhdvph27747;

import com.example.demo.model.ChiTietGiay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SPBanChay extends JpaRepository<ChiTietGiay, UUID> {
//    @Query(value = "select  top(5) giay.ten_giay, chi_tiet_giay.gia_ban,hinh_anh.url1 from giay join chi_tiet_giay on giay.id_giay = chi_tiet_giay.id_giay\n" +
//            "\t\t  join hinh_anh on chi_tiet_giay.id_hinh_anh = hinh_anh.id_hinh_anh\n" +
//            "\t\t  order by chi_tiet_giay.gia_ban desc",nativeQuery = true)
//    List<getTopSP> getTop5s();

    @Query("select g.giay.tenGiay,g.giaBan,g.hinhAnh.url1 from ChiTietGiay g ")
    List<ChiTietGiay> getTop5();
}
