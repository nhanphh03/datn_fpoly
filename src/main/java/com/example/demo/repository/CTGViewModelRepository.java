package com.example.demo.repository;

import com.example.demo.viewModel.CTGViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CTGViewModelRepository extends JpaRepository<CTGViewModel, UUID> {

    @Query(value = "SELECT chi_tiet_giay.id_giay,\n" +
            "       giay.ma_giay,\n" +
            "       giay.ten_giay,\n" +
            "       mau_sac.ten_mau,\n" +
            "       size.so_size,\n" +
            "       hinh_anh.url1,\n" +
            "       chi_tiet_giay.so_luong,\n" +
            "       chi_tiet_giay.gia_ban\n" +
            "FROM chi_tiet_giay\n" +
            "JOIN giay ON chi_tiet_giay.id_giay = giay.id_giay\n" +
            "JOIN mau_sac ON chi_tiet_giay.id_mau = mau_sac.id_mau\n" +
            "JOIN size ON chi_tiet_giay.id_size = size.id_size\n" +
            "JOIN hinh_anh ON chi_tiet_giay.id_hinh_anh = hinh_anh.id_hinh_anh\n" +
            "WHERE chi_tiet_giay.trang_thai = 1", nativeQuery = true)
    List<CTGViewModel> getAll();
}
