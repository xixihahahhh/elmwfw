package elm.service.imp;

import elm.mapper.CartMapper;
import elm.pojo.Cart;
import elm.pojo.Food;
import elm.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Override
    public List<Cart> getCartInfo(Cart cart) {
        return cartMapper.getCartInfo(cart);
    }

    @Override
    public int updateAddCart(Cart cart) {
        return cartMapper.updateAddCart(cart);
    }

    @Override
    public int updateMinusCart(Cart cart) {
        return cartMapper.updateMinusCart(cart);
    }

    @Override
    public int addCart(Cart cart) {
        return cartMapper.addCart(cart);
    }

    @Override
    public Food findByFoodId(Integer foodId) {
        return cartMapper.selectByFoodId(foodId);
    }

    @Override
    public Cart findCart(Cart cart) {
        return cartMapper.selectOne(cart);
    }

    @Override
    public int deleteCart(Cart cart) {
        return cartMapper.deleteById(cart);
    }

    @Override
    public void deleteCartByContions(Integer businessid, String userid) {
        cartMapper.deleteByContions(businessid, userid);
    }
}
