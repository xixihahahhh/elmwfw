package org.example.orderservice.feign;

import org.example.elmcommon.model.Food;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "food-service", path = "/food")
public interface FoodClient {
    @GetMapping("/{foodid}")
    Food getFoodById(@PathVariable("foodid") Integer foodid);
}

