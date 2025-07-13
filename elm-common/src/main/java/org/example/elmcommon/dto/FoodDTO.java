package org.example.elmcommon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.elmcommon.model.Food;

import java.io.Serializable;
import java.math.BigDecimal;

@Data  // Lombok 自动生成Getter/Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO implements Serializable {
    private Integer foodid;          // 与实体类字段名保持一致
    private String foodname;
    private String foodexplain;
    private String foodimg;
    private BigDecimal foodprice;
    private Integer businessid;
    private String remarks;
    private Integer categoryid;

    // 可选：添加从实体类转换的静态方法
    public static FoodDTO fromEntity(Food food) {
        if (food == null) return null;
        return new FoodDTO(
                food.getFoodid(),
                food.getFoodname(),
                food.getFoodexplain(),
                food.getFoodimg(),
                food.getFoodprice(),
                food.getBusinessid(),
                food.getRemarks(),
                food.getCategoryid()
        );
    }
}