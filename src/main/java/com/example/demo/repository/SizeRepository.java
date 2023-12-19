package com.example.demo.repository;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SizeRepository extends JpaRepository<Size, UUID> {

    List<Size> findByTrangThai(int trangThai);

    List<Size> findByTrangThaiOrderByMaSize(int trangThai);

    List<Size> findBySoSizeOrMaSize(Integer selectedSize,String maSize);
    @Query(value = "select ctg \n" +
            "from Size s join ChiTietGiay ctg on ctg.size.idSize = s.idSize \n" +
            "join MauSac ms on ctg.mauSac.idMau = ms.idMau \n" +
            "where ctg.giay.idGiay = ?1 \n" +
            "and ms.tenMau = ?2 \n " +
            "and  ctg.soLuong > 0 and ctg.trangThai = 1 " +
            "order by s.soSize asc" )
    List<ChiTietGiay> findByIdGiayAndMauSac2(UUID idGiay, String mauSac);

    Size findBySoSize(int soSize);

    List<Size> findAllByOrderByTgThemDesc();

    Size findByMaSize(String maSize);
}
