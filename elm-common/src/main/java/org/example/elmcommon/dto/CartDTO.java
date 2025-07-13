package org.example.elmcommon.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartDTO implements Serializable {
    private Integer cartid;      // 购物车项ID
    private Integer foodid;     // 食品ID
    private Integer businessid; // 商家ID
    private String userid;      // 用户ID
    private Integer quantity;   // 数量

    // 可选：关联的食品详细信息（通过Feign从food-service获取）
    private FoodDTO food;
}