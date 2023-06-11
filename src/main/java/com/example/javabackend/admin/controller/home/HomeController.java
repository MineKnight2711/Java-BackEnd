package com.example.javabackend.admin.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeController {
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
        return "/product/index";
    }

    @GetMapping("/qltopping")
    public String qlTopping(Model m){
        return "/manage/topping/index";
    }

    @GetMapping("/themtopping")
    public String addTopping(Model m){
        return "/topping/index";
    }

    @GetMapping("/themnguoiquantri")
    public String addAdmin(Model m){
        return "/account/index";
    }

}
