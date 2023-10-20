package com.example.demo.service;

import com.example.demo.model.Giay;
import com.example.demo.viewModel.CTGViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CTGViewModelService {
    public List<CTGViewModel> getAll();

    public Page<CTGViewModel> getAllPage(Pageable pageable);

    public List<CTGViewModel> getAllSoldOff();

    CTGViewModel findByIDGiay(UUID idGiay);

    List<CTGViewModel> findByIDHang(String idHang);

    Page<CTGViewModel> getAllByPriceHighToLow(Pageable pageable);

    Page<CTGViewModel> getAllByPriceLowToHigh(Pageable pageable);


}
