package com.example.demo.repository.thanhdvph27747;

import com.example.demo.model.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChiSoHoaDon extends JpaRepository<HoaDon,UUID>{
    @Query(value = "select COUNT(id_hd)\n" +
            "from hoa_don where trang_thai =3 or trang_thai=4",nativeQuery = true)
    Integer getAllHoaDonCho();

    @Query(value = "select COUNT(id_hd)\n" +
            "from hoa_don where trang_thai =1 or trang_thai=2",nativeQuery = true)
    Integer getAllHoaDonDaThanhToan();

}
