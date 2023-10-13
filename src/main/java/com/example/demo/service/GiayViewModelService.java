package com.example.demo.service;

import com.example.demo.viewModel.GiayViewModel;

import java.util.List;

public interface GiayViewModelService {

    List<GiayViewModel> getAll(String name);
}
