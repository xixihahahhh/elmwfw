package elm.mapper;

import elm.pojo.Cart;
import elm.pojo.Food;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {
    //获取用户的购物车信息
    @Select("select * from cart where businessId=#{businessid} and userId=#{userid}")
    public List<Cart>getCartInfo(Cart cart);

    @Select("select * from food where foodId = #{foodid}")
    Food selectByFoodId(Integer foodid);

    //更新购物车数量+1
    @Update("update cart set quantity= quantity+1 where foodid=#{foodid} and businessid=#{businessid} and userid=#{userid}")
    public int updateAddCart(Cart cart);

    @Update("update cart set quantity= quantity-1 where foodid=#{foodid} and businessid=#{businessid} and userid=#{userid}")
    public int updateMinusCart(Cart cart);

    @Insert("insert into cart values(null,#{foodid},#{businessid},#{userid},1)")
    public int addCart(Cart cart);

    @Select("select * from cart where businessId=#{businessid} and userId=#{userid} and foodId = #{foodid}")
    Cart selectOne(Cart cart);

    @Delete("delete from cart where foodid=#{foodid} and businessid=#{businessid} and userid=#{userid}")
    int deleteById(Cart cart);

    @Delete("delete from cart where businessid=#{businessid} and userid=#{userid}")
    void deleteByContions(@Param("businessid") Integer businessid,@Param("userid") String userid);
}
