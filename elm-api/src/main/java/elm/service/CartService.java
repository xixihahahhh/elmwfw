package elm.service;

import elm.pojo.Cart;
import elm.pojo.Food;

import java.util.List;

public interface CartService {
    //获取用户的购物车信息
    public List<Cart> getCartInfo(Cart cart);
    public int updateAddCart(Cart cart);

    public int updateMinusCart(Cart cart);
    public int addCart(Cart cart);

    Food findByFoodId(Integer foodId);

    Cart findCart(Cart cart);

    int deleteCart(Cart cart);
    void deleteCartByContions(Integer businessid,String userid);
}
