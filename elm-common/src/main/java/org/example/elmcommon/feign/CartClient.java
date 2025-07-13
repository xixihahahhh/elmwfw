package org.example.elmcommon.feign;

import org.example.elmcommon.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "cart-service", path = "/cart")
public interface CartClient {
    @PostMapping("/info")
    List<CartDTO> getCartInfo(@RequestBody CartDTO cartDTO);
}