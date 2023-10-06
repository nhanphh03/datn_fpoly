package com.example.demo.buyerController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buyer")
public class DetailProductController {

    @GetMapping("/detail")
    private String getFormDetail(Model model){
        return "online/detail-product";
    }

}
