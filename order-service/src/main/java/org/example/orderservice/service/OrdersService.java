package org.example.orderservice.service;

import org.example.elmcommon.model.Food;
import org.example.orderservice.dto.BusinessDTO;
import org.example.orderservice.model.Orderdetailet;
import org.example.orderservice.model.Orders;

import java.util.List;

public interface OrdersService {
    void createdOrder(Orders orders);
    void createdOrderDetailet(Orderdetailet orderdetailet);
    List<Orders> findAllOrders(String userid, Integer orderstate);
    List<Orderdetailet> findAllOrderDetail(Integer orderid);
    Food findFood(Integer foodid);
    BusinessDTO findBusiness(Integer businessid);
    void updateOrdersState(Integer orderid);
    Orders findOne(Integer orderid);
    boolean deleteOrder(Integer orderid);
}