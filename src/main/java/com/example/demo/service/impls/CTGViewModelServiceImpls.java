package com.example.demo.service.impls;

import com.example.demo.repository.CTGViewModelRepository;
import com.example.demo.service.CTGViewModelService;
import com.example.demo.viewModel.CTGViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CTGViewModelServiceImpls implements CTGViewModelService {

    @Autowired
    private CTGViewModelRepository ctgViewModelRepository;

    @Override
    public List<CTGViewModel> getAll() {
        return ctgViewModelRepository.getAll();
    }
}
