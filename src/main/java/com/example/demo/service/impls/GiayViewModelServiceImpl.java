package com.example.demo.service.impls;

import com.example.demo.repository.GiayViewModelRepository;
import com.example.demo.service.GiayViewModelService;
import com.example.demo.viewModel.GiayViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiayViewModelServiceImpl implements GiayViewModelService {
    @Autowired
    private GiayViewModelRepository giayViewModelRepository;

    @Override
    public List<GiayViewModel> getAll(String name) {
        return giayViewModelRepository.searchByTenGiay(name);
    }
}
