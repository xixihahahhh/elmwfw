package org.example.cartservice.service.impl;

import org.example.cartservice.model.Cart;
import org.example.cartservice.repository.CartRepository;
import org.example.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> getCartInfo(Cart cart) {
        return cartRepository.findByUseridAndBusinessid(cart.getUserid(), cart.getBusinessid());
    }

    @Override
    @Transactional
    public int updateAddCart(Cart cart) {
        Cart existingCart = cartRepository.findByUseridAndFoodid(cart.getUserid(), cart.getFoodid());
        if (existingCart != null) {
            existingCart.setQuantity(existingCart.getQuantity() + 1);
            cartRepository.save(existingCart);
            return 1;
        }
        return 0;
    }

    @Override
    @Transactional
    public int updateMinusCart(Cart cart) {
        Cart existingCart = cartRepository.findByUseridAndFoodid(cart.getUserid(), cart.getFoodid());
        if (existingCart != null && existingCart.getQuantity() > 1) {
            existingCart.setQuantity(existingCart.getQuantity() - 1);
            cartRepository.save(existingCart);
            return 1;
        } else if (existingCart != null) {
            cartRepository.delete(existingCart);
            return 1;
        }
        return 0;
    }

    @Override
    @Transactional
    public int addCart(Cart cart) {
        cartRepository.save(cart);
        return 1;
    }

    @Override
    public Cart findCart(Cart cart) {
        return cartRepository.findByUseridAndFoodid(cart.getUserid(), cart.getFoodid());
    }

    @Override
    @Transactional
    public int deleteCart(Cart cart) {
        Cart existing = cartRepository.findByUseridAndFoodid(cart.getUserid(), cart.getFoodid());
        if (existing != null) {
            cartRepository.delete(existing);
            return 1;
        }
        return 0;
    }

    @Override
    @Transactional
    public void deleteCartByConditions(Integer businessId, String userId) {
        cartRepository.deleteByUseridAndBusinessid(userId, businessId);
    }
}
