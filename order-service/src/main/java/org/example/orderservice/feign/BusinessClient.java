package org.example.orderservice.feign;

import org.example.orderservice.dto.BusinessDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "business-service", path = "/businesses")
public interface BusinessClient {
    @GetMapping("/{businessid}")
    BusinessDTO getBusinessById(@PathVariable("businessid") Integer businessid);
}
