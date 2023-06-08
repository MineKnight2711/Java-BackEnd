package com.example.javabackend.admin.controller;

import com.example.javabackend.entity.Dishes;
import com.example.javabackend.modules.category.service.CategoryService;
import com.example.javabackend.modules.dishes.DTO.DishDto;
import com.example.javabackend.modules.dishes.controller.DishApi;
import com.example.javabackend.modules.size.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;


@Controller
@RequestMapping("/")
public class DishController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private DishApi dishApi;
    @GetMapping("/themsanpham")
    public String addSanpham(Model m){
        m.addAttribute("dish",new DishDto());
        m.addAttribute("categories", categoryService.getAllCategories());
        m.addAttribute("sizes", sizeService.getAll());
        return "/product/index";
    }
    @PostMapping("/themsanpham")
    public String addBook (@RequestParam("file") MultipartFile file, @ModelAttribute("dish") @Valid DishDto dto, BindingResult result, Model model, RedirectAttributes rec) throws IOException {
        if(result.hasErrors()){
            model.addAttribute("sizes", sizeService.getAll());
            model.addAttribute("categories", categoryService.getAllCategories());
            rec.addAttribute("addresult", "Có lỗi xảy ra, vui lòng kiểm tra lại thông tin");
            return "/product/index";
        }
        else{
            rec.addAttribute("addresult", "Đã thêm món ăn thành công");
            dishApi.createDish(file,dto.dishName,dto.price, dto.sizeId,dto.categoryId);
            return "redirect:/themsanpham";
        }
    }
}
