package elm.controller;

import elm.pojo.Cart;
import elm.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;
    @RequestMapping("getCartInfo")
    public List<Cart> getCartInfo(@RequestBody Cart cart){
        System.out.println(cart);
        List<Cart> cartInfo = cartService.getCartInfo(cart);
//        System.out.println(cartInfo);
        for(Cart cart1 : cartInfo){
            cart1.setFood(cartService.findByFoodId(cart1.getFoodid()));
        }
        return cartInfo;
    }
    @RequestMapping("updateAddCart")
    public int updateAddCart(@RequestBody Cart cart){
        Cart cart1 = cartService.findCart(cart);
        if(cart1!=null){
            return cartService.updateAddCart(cart);
        }else{
            return cartService.addCart(cart);
        }
    }

    @RequestMapping("updateMinusCart")
    public int updateMinusCart(@RequestBody Cart cart){
        return cartService.updateMinusCart(cart);
    }


    @RequestMapping("delete")
    public int delete(@RequestBody Cart cart){
        return cartService.deleteCart(cart);
    }

    @RequestMapping("addCart")
    public int addCart(Cart cart){
        return cartService.addCart(cart);
    }

    @RequestMapping("/delAll")
    public boolean delAll(Integer businessid, String userid){
        System.out.println("接收到的值为："+businessid+userid);
        try{
            cartService.deleteCartByContions(businessid, userid);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
