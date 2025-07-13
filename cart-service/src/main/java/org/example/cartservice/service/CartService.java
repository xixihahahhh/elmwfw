package org.example.cartservice.service;

import org.example.cartservice.model.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getCartInfo(Cart cart);

    int updateAddCart(Cart cart);

    int updateMinusCart(Cart cart);

    int addCart(Cart cart);


    Cart findCart(Cart cart);

    int deleteCart(Cart cart);

    void deleteCartByConditions(Integer businessId, String userId);
}