package org.example.businessservice.service.impl;

import org.example.businessservice.model.Business;
import org.example.businessservice.repository.BusinessRepository;
import org.example.businessservice.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessRepository businessRepository;

    public List<Business> findAll() {
        return businessRepository.findAll();  // 对应原 selectAll()
    }

    public List<Business> getIndexBusiness() {
        return businessRepository.findIndexBusiness();  // 首页推荐
    }

    public List<Business> getBusinessByType(int typeid) {
        return businessRepository.findByOrdertypeid(typeid);  // 按分类查询
    }

    public Business getBusinessById(int bid) {
        return businessRepository.findByBusinessid(bid);  // 查询商家详情（含商品列表）
    }

}
