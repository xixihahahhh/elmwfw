package org.example.cartservice.controller;

import org.example.cartservice.model.Cart;
import org.example.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart") // 微服务统一前缀
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/getCartInfo")
    public List<Cart> getCartInfo(@RequestBody Cart cart) {
        List<Cart> cartInfo = cartService.getCartInfo(cart);
        return cartInfo;
    }

    @PostMapping("/updateAddCart")
    public int updateAddCart(@RequestBody Cart cart) {
        Cart existingCart = cartService.findCart(cart);
        return (existingCart != null) ? cartService.updateAddCart(cart) : cartService.addCart(cart);
    }

    @PostMapping("/updateMinusCart")
    public int updateMinusCart(@RequestBody Cart cart) {
        return cartService.updateMinusCart(cart);
    }

    @PostMapping("/delete")
    public int delete(@RequestBody Cart cart) {
        return cartService.deleteCart(cart);
    }

    @PostMapping("/addCart")
    public int addCart(@RequestBody Cart cart) {
        return cartService.addCart(cart);
    }

    @DeleteMapping("/delAll")
    public boolean deleteAll(@RequestParam Integer businessId, @RequestParam String userId) {
        try {
            cartService.deleteCartByConditions(businessId, userId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
