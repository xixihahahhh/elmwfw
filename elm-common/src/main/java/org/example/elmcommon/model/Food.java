package org.example.elmcommon.model;

import java.math.BigDecimal;
import java.io.Serializable;

/**
 * 食品实体类 - 用于微服务间共享
 */
public class Food implements Serializable {  // 实现Serializable以便远程传输
    private Integer foodid;
    private String foodname;
    private String foodexplain;
    private String foodimg;
    private BigDecimal foodprice;
    private Integer businessid;
    private String remarks;
    private Integer categoryid;

    // 无参构造函数
    public Food() {
    }

    // 全参构造函数 (可选)
    public Food(Integer foodid, String foodname, String foodexplain,
                String foodimg, BigDecimal foodprice, Integer businessid,
                String remarks, Integer categoryid) {
        this.foodid = foodid;
        this.foodname = foodname;
        this.foodexplain = foodexplain;
        this.foodimg = foodimg;
        this.foodprice = foodprice;
        this.businessid = businessid;
        this.remarks = remarks;
        this.categoryid = categoryid;
    }

    // Getter和Setter方法
    public Integer getFoodid() {
        return foodid;
    }

    public void setFoodid(Integer foodid) {
        this.foodid = foodid;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname == null ? null : foodname.trim();
    }

    public String getFoodexplain() {
        return foodexplain;
    }

    public void setFoodexplain(String foodexplain) {
        this.foodexplain = foodexplain == null ? null : foodexplain.trim();
    }

    public String getFoodimg() {
        return foodimg;
    }

    public void setFoodimg(String foodimg) {
        this.foodimg = foodimg == null ? null : foodimg.trim();
    }

    public BigDecimal getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(BigDecimal foodprice) {
        this.foodprice = foodprice;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    // 可选：重写toString方法便于日志打印
    @Override
    public String toString() {
        return "Food{" +
                "foodid=" + foodid +
                ", foodname='" + foodname + '\'' +
                ", foodexplain='" + foodexplain + '\'' +
                ", foodimg='" + foodimg + '\'' +
                ", foodprice=" + foodprice +
                ", businessid=" + businessid +
                ", remarks='" + remarks + '\'' +
                ", categoryid=" + categoryid +
                '}';
    }
}