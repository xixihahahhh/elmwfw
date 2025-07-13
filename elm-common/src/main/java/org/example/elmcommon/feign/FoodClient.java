package org.example.elmcommon.feign;

import org.example.elmcommon.dto.FoodDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "food-service", path = "/foods")
public interface FoodClient {

    /**
     * 根据食品ID获取食品信息
     * @param foodid 食品ID
     * @return FoodDTO
     */
    @GetMapping("/{foodid}")
    FoodDTO getFoodById(@PathVariable("foodid") Integer foodid);

    // 其他接口...
    // @PostMapping
    // @DeleteMapping("/{foodid}")
}