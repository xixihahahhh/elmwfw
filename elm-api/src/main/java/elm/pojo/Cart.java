package elm.pojo;

public class Cart {
    private Integer cartid;

    private Integer foodid;

    private Integer businessid;

    private String userid;

    private Integer quantity;
    private Food food;
    
    
    @Override
	public String toString() {
		return "Cart [cartid=" + cartid + ", foodid=" + foodid + ", businessid=" + businessid + ", userid=" + userid
				+ ", quantity=" + quantity + ", food=" + food + "]";
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}