package com.example.demo.service;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.viewModel.GiayViewModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GiayViewModelService {

    List<GiayViewModel> getAll(String name);

    GiayViewModel findByIdGiay(UUID id);
}
