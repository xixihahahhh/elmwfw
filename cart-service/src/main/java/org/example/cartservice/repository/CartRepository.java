package org.example.cartservice.repository;

import org.example.cartservice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUseridAndBusinessid(String userid, Integer businessid);
    Cart findByUseridAndFoodid(String userid, Integer foodid);
    void deleteByUseridAndBusinessid(String userid, Integer businessid);
}