package com.example.javabackend.modules.size.controller;

import com.example.javabackend.entity.Size;
import com.example.javabackend.modules.size.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/size")
public class SizeController {
    @Autowired
    private SizeService sizeService;
    @GetMapping()
    public List<Size> getAll() {
        return this.sizeService.getAll();
    }
    @GetMapping("/id/{id}")
    public Size getById(@PathVariable("id") Long id) {
        return this.sizeService.getByid(id);
    }
    @PostMapping()
    public Size createSize(@RequestBody Size size) {
        return this.sizeService.create(size);
    }

    @PutMapping()
    public Size editSize(@RequestBody Size size) {
        return this.sizeService.edit(size);
    }
}
