package com.example.demo.repository;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.viewModel.CTGViewModel;
import com.example.demo.model.Giay;
import com.example.demo.viewModel.CTSPViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GiayChiTietRepository extends JpaRepository<ChiTietGiay, UUID> {
    //    @Query(value = "select chi_tiet_giay.id_giay,\n" +
//                    "giay.ma_giay,giay.ten_giay,\n" +
//                    "mau_sac.ten_mau,\n" +
//                    "size.so_size,\n" +
//                    "hinh_anh.url1,\n" +
//                    "chi_tiet_giay.so_luong,chi_tiet_giay.gia_ban\n" +
//                    "from chi_tiet_giay join giay on chi_tiet_giay.id_giay = giay.id_giay\n" +
//                    "join mau_sac on chi_tiet_giay.id_mau= mau_sac.id_mau\n" +
//                    "join size on chi_tiet_giay.id_size = size.id_size\n" +
//                    "join hinh_anh on chi_tiet_giay.id_hinh_anh = hinh_anh.id_hinh_anh\n" +
//                    "where chi_tiet_giay.trang_thai =1",nativeQuery = true)
//    List<CTSPViewModel> getAll();
}
