package com.example.demo.service;

import com.example.demo.model.Giay;
import com.example.demo.viewModel.CTGViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CTGViewModelService {
    List<CTGViewModel> getAll();

    List<CTGViewModel> getAllProductPromotion();

    List<CTGViewModel> getAllProductNonPromotion();

    Page<CTGViewModel> getAllPage(Pageable pageable);

    List<CTGViewModel> getAllSoldOff();

    CTGViewModel findByIDGiayAndMau(UUID idGiay, UUID idMau);

    List<CTGViewModel> findByIDHang(UUID idHang);

    Page<CTGViewModel> getAllByPriceHighToLow(Pageable pageable);

    Page<CTGViewModel> getAllByPriceLowToHigh(Pageable pageable);

    List<CTGViewModel> getAllOrderTgNhap();

    List<CTGViewModel> getAllOrderBestSeller();


}
