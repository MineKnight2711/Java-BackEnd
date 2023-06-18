package com.example.javabackend.modules.size.service;

import com.example.javabackend.entity.Size;
import com.example.javabackend.modules.size.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    public List<Size> getAll() {
        return this.sizeRepository.findAll();
    }

    public Size getByid(Long id) {
        return this.sizeRepository.getById(id);
    }

    public Size create(Size size) {
        return this.sizeRepository.save(size);
    }

    public Size edit(Size size) {
        return this.sizeRepository.save(size);
    }

    //Delete size web
    public void deleteSize(Long id) {
        sizeRepository.deleteById(id);
    }
}
