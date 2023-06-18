package com.example.javabackend.admin.controller;

import com.example.javabackend.entity.*;
import com.example.javabackend.modules.category.service.CategoryService;
import com.example.javabackend.modules.dishes.DTO.DishDto;
import com.example.javabackend.modules.dishes.service.DishService;
import com.example.javabackend.modules.size.service.SizeService;
import com.example.javabackend.modules.topping.Dto.ToppingDto;
import com.example.javabackend.modules.topping.service.ToppingService;
import com.example.javabackend.modules.user.DTO.AccountTypeDTO;
import com.example.javabackend.modules.user.DTO.AccountsDTO;
import com.example.javabackend.modules.user.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    ToppingService toppingService;

    @Autowired
    DishService dishService;
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

    @GetMapping("/qltopping")
    public String qlTopping(Model model) {
        List<Topping> toppings = toppingService.getAll();
        model.addAttribute("toppings", toppings);
        return "manage/topping/index";
    }
    @GetMapping("/qldanhmuc")
    public String qlDanhMuc(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "manage/category/index";
    }
    @GetMapping("/qlsanpham")
    public String qlSanPham(Model model) {
        List<Dishes> dishes = dishService.getAllDishes();
        model.addAttribute("dishes", dishes);
        return "manage/product/index";
    }

    @GetMapping("/qlsize")
    public String qlSize(Model model) {
        List<Size> sizes = sizeService.getAll();
        model.addAttribute("sizes", sizes);
        return "manage/size/index";
    }

    @GetMapping("/edit-topping/{id}")
    public String qlToppingWW(Model model) {
        List<Topping> toppings = toppingService.getAll();
        model.addAttribute("topping", toppings);
        return "manage/topping/edit";
    }

}
