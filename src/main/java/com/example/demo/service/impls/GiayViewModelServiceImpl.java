package com.example.demo.service.impls;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.repository.GiayViewModelRepository;
import com.example.demo.service.GiayViewModelService;
import com.example.demo.viewModel.GiayViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GiayViewModelServiceImpl implements GiayViewModelService {
    @Autowired
    private GiayViewModelRepository giayViewModelRepository;

    @Override
    public List<GiayViewModel> getAll(String name) {
        return giayViewModelRepository.searchByTenGiay(name);
    }

    @Override
    public GiayViewModel findByIdGiay(UUID id) {
        return giayViewModelRepository.findByIdGiay(id);
    }
}
