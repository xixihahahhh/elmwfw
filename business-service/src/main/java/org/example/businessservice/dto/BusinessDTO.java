package org.example.businessservice.dto;

import java.math.BigDecimal;

/**
 * 商家数据传输对象（用于跨微服务通信）
 */
public class BusinessDTO {
    private Integer businessid;
    private String businessname;
    private String businessaddress;
    private String businessexplain;
    private String businessimg;
    private Integer ordertypeid;
    private BigDecimal starprice;
    private BigDecimal deliveryprice;
    private String remarks;
    private Integer typeid;

    // 关联商家类型名称（可选，根据需求添加）
    private String businessTypeName;

    // 空构造器（JSON序列化需要）
    public BusinessDTO() {}

    // getters 和 setters
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

    public String getBusinessexplain() {
        return businessexplain;
    }

    public void setBusinessexplain(String businessexplain) {
        this.businessexplain = businessexplain;
    }

    public String getBusinessimg() {
        return businessimg;
    }

    public void setBusinessimg(String businessimg) {
        this.businessimg = businessimg;
    }

    public Integer getOrdertypeid() {
        return ordertypeid;
    }

    public void setOrdertypeid(Integer ordertypeid) {
        this.ordertypeid = ordertypeid;
    }

    public BigDecimal getStarprice() {
        return starprice;
    }

    public void setStarprice(BigDecimal starprice) {
        this.starprice = starprice;
    }

    public BigDecimal getDeliveryprice() {
        return deliveryprice;
    }

    public void setDeliveryprice(BigDecimal deliveryprice) {
        this.deliveryprice = deliveryprice;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    // 可选：添加toString方法便于调试
    @Override
    public String toString() {
        return "BusinessDTO{" +
                "businessid=" + businessid +
                ", businessname='" + businessname + '\'' +
                ", businessaddress='" + businessaddress + '\'' +
                '}';
    }
}