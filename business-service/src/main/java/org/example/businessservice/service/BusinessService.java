package org.example.businessservice.service;

import org.example.businessservice.model.Business;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.List;

public interface BusinessService {

    List<Business> findAll();

    //检索首页商家信息
    public List<Business> getIndexBusiness();
    //根据点餐分类检索商家信息
    public List<Business>getBusinessByType(int typeid);
    //根据商家id检索商家信息
    public Business getBusinessById(int bid);

}
