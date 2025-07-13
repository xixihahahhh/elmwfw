package org.example.orderservice.model;

import org.example.elmcommon.model.Food;

import javax.persistence.*;

// Orderdetailet.java
@Entity
@Table(name = "orderdetailet")
public class Orderdetailet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer odid;

    private Integer orderid;
    private Integer foodid;
    private Integer quantity;

    private Food food;
    // Getters and Setters
    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public  Orderdetailet(){}

    public Integer getOdid() {
        return odid;
    }

    public void setOdid(Integer odid) {
        this.odid = odid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getFoodid() {
        return foodid;
    }

    public void setFoodid(Integer foodid) {
        this.foodid = foodid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}