package elm.service;

import elm.pojo.Business;
import elm.pojo.Food;
import elm.pojo.Orderdetailet;
import elm.pojo.Orders;

import java.util.List;

public interface OrdersService {
    void createdOrder(Orders orders);

    void createdOrderDetailet(Orderdetailet orderdetailet);

    List<Orders> findAllOrders(String userid,Integer orderstate);

    List<Orderdetailet> findAllOrderDetail(Integer orderid);

    Food findFood(Integer foodid);

    Business findBusiness(Integer businessid);

    void updateOrdersState(Integer orderid);

    Orders findOne(Integer orderid);

    boolean deleteOrder(Integer orderid);
}
