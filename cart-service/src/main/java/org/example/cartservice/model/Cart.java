package org.example.cartservice.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {  // 实现Serializable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartid;

    private Integer foodid;
    private Integer businessid;
    private String userid;
    private Integer quantity;  // 必须包含此字段

    // --------------------------
    // 必须添加Getter和Setter
    // --------------------------
    public Integer getCartid() {
        return cartid;
    }

    public void setCartid(Integer cartid) {
        this.cartid = cartid;
    }

    public Integer getFoodid() {
        return foodid;
    }

    public void setFoodid(Integer foodid) {
        this.foodid = foodid;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getQuantity() {  // Getter
        return quantity;
    }

    public void setQuantity(Integer quantity) {  // Setter（报错缺失的方法）
        this.quantity = quantity;
    }
}
