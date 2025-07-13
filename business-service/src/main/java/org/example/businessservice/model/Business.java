package org.example.businessservice.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "business")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "businessid")
    private Integer businessid;

    @Column(name = "businessname")
    private String businessname;

    @Column(name = "businessaddress")
    private String businessaddress;

    @Column(name = "businessexplain")
    private String businessexplain;

    @Column(name = "businessimg")
    private String businessimg;

    @Column(name = "ordertypeid")
    private Integer ordertypeid;

    @Column(name = "starprice")
    private BigDecimal starprice;

    @Column(name = "deliveryprice")
    private BigDecimal deliveryprice;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "typeid", insertable = false, updatable = false)
    private Integer typeid;

    // 关联商家类型（多对一）
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeid", referencedColumnName = "typeid")
    private BusinessType businessType;

    // 关联商品（一对多）
    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY)
    private List<Food> foodList;

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
        this.businessname = businessname == null ? null : businessname.trim();
    }

    public String getBusinessaddress() {
        return businessaddress;
    }

    public void setBusinessaddress(String businessaddress) {
        this.businessaddress = businessaddress == null ? null : businessaddress.trim();
    }

    public String getBusinessexplain() {
        return businessexplain;
    }

    public void setBusinessexplain(String businessexplain) {
        this.businessexplain = businessexplain == null ? null : businessexplain.trim();
    }

    public String getBusinessimg() {
        return businessimg;
    }

    public void setBusinessimg(String businessimg) {
        this.businessimg = businessimg == null ? null : businessimg.trim();
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
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }
}