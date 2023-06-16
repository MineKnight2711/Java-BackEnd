package com.example.javabackend.admin.controller;

import com.example.javabackend.entity.AccountType;
import com.example.javabackend.entity.Topping;
import com.example.javabackend.modules.category.service.CategoryService;
import com.example.javabackend.modules.dishes.DTO.DishDto;
import com.example.javabackend.modules.size.service.SizeService;
import com.example.javabackend.modules.topping.service.ToppingService;
import com.example.javabackend.modules.user.DTO.AccountTypeDTO;
import com.example.javabackend.modules.user.DTO.AccountsDTO;
import com.example.javabackend.modules.user.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    ToppingService toppingService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SizeService sizeService;

    @GetMapping("/")
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

    @GetMapping("/qlsanpham")
    public String qlSanPham(Model m){
        return "/manage/product/index";
    }

    @GetMapping("/qltopping")
    public String showAllTopping(Model model) {
        List<Topping> toppings = toppingService.getAll();
        model.addAttribute("toppings", toppings);
        model.addAttribute("toppingsTitle", "Danh sách các topping");
        return "manage/topping/index";
    }

}
