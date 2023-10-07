package com.example.demo.buyerController;


import com.example.demo.service.CTGViewModelService;
import com.example.demo.viewModel.CTGViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/buyer")
public class HomeController {

    @Autowired
    private CTGViewModelService ctgViewModelService;

    @RequestMapping(value = {"", "/", "/indexBuyer", "/homeBuyer"})
    public String home(Model model){
        List<CTGViewModel> listCTGViewModel = ctgViewModelService.getAll();
        model.addAttribute("listCTGModel",listCTGViewModel);
        return "online/index";
    }
}
