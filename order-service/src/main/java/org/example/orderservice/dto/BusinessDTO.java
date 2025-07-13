package org.example.orderservice.dto;

import java.math.BigDecimal;

/**
 * 商家数据传输对象（订单服务专用）
 * 仅包含订单业务所需的字段
 */
public class BusinessDTO {
    private Integer businessid;      // 商家ID（必须与business-service字段名一致）
    private String businessname;    // 商家名称
    private String businessaddress; // 配送地址（可选）
    private BigDecimal deliveryprice; // 配送费（订单计算需要）

    // 必须有无参构造器
    public BusinessDTO() {}

    //----------------------
    // Getter 和 Setter
    //----------------------
    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public String getBusinessaddress() {
        return businessaddress;
    }

    public void setBusinessaddress(String businessaddress) {
        this.businessaddress = businessaddress;
    }

    public BigDecimal getDeliveryprice() {
        return deliveryprice;
    }

    public void setDeliveryprice(BigDecimal deliveryprice) {
        this.deliveryprice = deliveryprice;
    }

    // 可选：添加toString()方便调试
    @Override
    public String toString() {
        return "BusinessDTO{" +
                "businessid=" + businessid +
                ", businessname='" + businessname + '\'' +
                ", deliveryprice=" + deliveryprice +
                '}';
    }
}