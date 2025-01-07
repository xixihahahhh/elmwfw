package elm.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Orders {
    private Integer orderid;

    private String userid;

    private Integer businessid;
    private Business business;
    private List<Orderdetailet> detailList; 

    private Date orderdate;

    private BigDecimal ordertotal;

    private Integer daid;

    private Integer orderstate;
    
    

    public List<Orderdetailet> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<Orderdetailet> detailList) {
		this.detailList = detailList;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public BigDecimal getOrdertotal() {
        return ordertotal;
    }

    public void setOrdertotal(BigDecimal ordertotal) {
        this.ordertotal = ordertotal;
    }

    public Integer getDaid() {
        return daid;
    }

    public void setDaid(Integer daid) {
        this.daid = daid;
    }

    public Integer getOrderstate() {
        return orderstate;
    }

    public void setOrderstate(Integer orderstate) {
        this.orderstate = orderstate;
    }
}