package com.example.javabackend.modules.deliver.controller;

import com.example.javabackend.entity.ResponseModel;
import com.example.javabackend.modules.deliver.dto.DeliverDTO;
import com.example.javabackend.modules.deliver.service.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/deliver")
public class DeliverController
{
    @Autowired
    private DeliverService deliverService;

    @GetMapping("/{id}")
    public ResponseModel getDeliverById(@PathVariable("id") Long id)
    {
        return deliverService.getDeliverById(id);
    }
    @PostMapping
    public ResponseModel createNewDeliver(@RequestBody DeliverDTO dto)
    {
        return deliverService.createNewDeliver(dto);
    }
    @GetMapping("/request-publicKey")
    public ResponseModel loginByPhoneNumber()
    {
        return deliverService.requestPublicKey();
    }
    @PostMapping("/login")

    public ResponseModel loginByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber)
    {
        return deliverService.login(phoneNumber);
    }
    @PutMapping("/update-location/{id}")
        public ResponseModel createNewDeliver(@PathVariable("id") Long id,
                                          @RequestParam("latitude") Double latitude,
                                          @RequestParam("longitude") Double longitude)
    {
        return deliverService.updateLocation(id,latitude,longitude);
    }

}
