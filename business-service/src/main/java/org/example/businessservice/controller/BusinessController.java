package org.example.businessservice.controller;

import org.example.businessservice.model.Business;
import org.example.businessservice.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/businesses")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @GetMapping
    public List<Business> getAllBusinesses() {
        return businessService.findAll();
    }

    @GetMapping("/featured")
    public List<Business> getFeaturedBusinesses() {
        return businessService.getIndexBusiness(); // 首页推荐商家
    }

    @GetMapping("/type/{typeid}")
    public List<Business> getBusinessesByType(@PathVariable Integer typeid) {
        return businessService.getBusinessByType(typeid);
    }

    @GetMapping("/{bid}")
    public Business getBusinessById(@PathVariable Integer bid) {
        return businessService.getBusinessById(bid);
    }
}