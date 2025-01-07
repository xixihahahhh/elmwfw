package elm.service.imp;

import elm.mapper.OrderMapper;
import elm.pojo.Business;
import elm.pojo.Food;
import elm.pojo.Orderdetailet;
import elm.pojo.Orders;
import elm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService{

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public void createdOrder(Orders orders) {
        orderMapper.insertOrder(orders);
    }

    @Override
    public void createdOrderDetailet(Orderdetailet orderdetailet) {
        orderMapper.insertOrderDetailet(orderdetailet);
    }

    @Override
    public List<Orders> findAllOrders(String userid, Integer orderstate) {
        return orderMapper.selectAllOrders(userid, orderstate);
    }

    @Override
    public List<Orderdetailet> findAllOrderDetail(Integer orderid) {
        return orderMapper.selectAllOrderDetail(orderid);
    }

    @Override
    public Food findFood(Integer foodid) {
        return orderMapper.selectFood(foodid);
    }

    @Override
    public Business findBusiness(Integer businessid) {
        return orderMapper.selectBusiness(businessid);
    }

    @Override
    public void updateOrdersState(Integer orderid) {
        orderMapper.updateOrders(orderid);
    }

    @Override
    public Orders findOne(Integer orderid) {
        return orderMapper.selectOne(orderid);
    }

    @Override
    public boolean deleteOrder(Integer orderid) {
        try {
            // 先删除订单详情
            orderMapper.deleteOrderDetails(orderid);
            // 再删除订单
            orderMapper.deleteOrder(orderid);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
