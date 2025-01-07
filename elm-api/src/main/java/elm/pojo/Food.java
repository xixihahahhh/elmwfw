package elm.pojo;

import java.math.BigDecimal;

public class Food {
    private Integer foodid;

    private String foodname;

    private String foodexplain;

    private String foodimg;

    private BigDecimal foodprice;

    private Integer businessid;

    private String remarks;

    private Integer categoryid;

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
}