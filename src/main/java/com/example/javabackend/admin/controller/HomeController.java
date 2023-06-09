package com.example.javabackend.admin.controller;

import com.example.javabackend.modules.category.service.CategoryService;
import com.example.javabackend.modules.dishes.DTO.DishDto;
import com.example.javabackend.modules.size.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SizeService sizeService;
    @RequestMapping("/")
   public String trangchu(Model m) {
        String strTieuDe = "Day la trang chu";
        m.addAttribute("Tieu de trang", strTieuDe);
        return "indexSB";
    }

    @GetMapping("/admin")
    public String trangchuSB(Model m){
        return "indexSB";
    }
    @GetMapping("/themdanhmuc")
    public String addDanhmuc(Model m){
        return "/category/index";
    }
    @GetMapping("/themsanpham")
    public String addSanpham(Model m){
        m.addAttribute("dish",new DishDto());
        m.addAttribute("categories", categoryService.getAllCategories());
        m.addAttribute("sizes", sizeService.getAll());
        return "/product/index";
    }

    @GetMapping("/themtopping")
    public String addTopping(Model m){
        return "/topping/index";
    }

    @GetMapping("/themnguoiquantri")
    public String addAdmin(Model m){
        return "/account/index";
    }

//    @GetMapping("/login")
//    public String dangNhap(Model m){
//        return "/login/index";
//    }

}
