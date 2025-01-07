package elm.mapper;

import elm.pojo.Business;
import elm.pojo.Food;
import elm.pojo.Orderdetailet;
import elm.pojo.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("insert into orders values (null,#{userid},#{businessid},#{orderdate},#{ordertotal},#{daid},0)")
    @Options(useGeneratedKeys = true, keyProperty = "orderid", keyColumn = "orderId")
    void insertOrder(Orders orders);

    @Insert("insert into orderdetailet values (null,#{orderid},#{foodid},#{quantity})")
    void insertOrderDetailet(Orderdetailet orderdetailet);


    @Select("select * from orders where userId = #{userid} and orderState =#{orderstate}")
    List<Orders> selectAllOrders(@Param("userid") String userid,@Param("orderstate") Integer orderstate);

    @Select("select * from orders where orderId = #{orderid}")
    Orders selectOne(Integer orderid);

    @Select("select * from orderdetailet where orderId = #{orderid}")
    List<Orderdetailet> selectAllOrderDetail(Integer orderid);

    @Select("select * from food where foodId = #{foodid}")
    Food selectFood(Integer foodid);

    @Select("select * from business where businessId = #{businessid}")
    Business selectBusiness(Integer businessid);

    @Update("update orders set orderState = 1 where orderId = #{orderid}")
    void updateOrders(Integer orderid);

    void deleteOrder(Integer orderid);

    void deleteOrderDetails(Integer orderid);
}
