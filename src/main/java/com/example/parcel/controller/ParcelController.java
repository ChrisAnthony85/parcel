package com.example.parcel.controller;

import com.example.parcel.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("parcel")
public class ParcelController {

    private final ParcelService parcelService;

    @Autowired
    public ParcelController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @GetMapping("/cost")
    String calculateCost(@RequestParam String weight,
                         @RequestParam String height,
                         @RequestParam String width,
                         @RequestParam String length,
                         @RequestParam String voucher) {
        return parcelService.getCost(weight, height, width, length, voucher);
    }
}
