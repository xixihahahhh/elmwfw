package org.example.orderservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.elmcommon.model.Food;
import org.example.orderservice.dto.BusinessDTO;
import org.example.orderservice.feign.BusinessClient;
import org.example.orderservice.feign.FoodClient;
import org.example.orderservice.model.Orderdetailet;
import org.example.orderservice.model.Orders;
import org.example.orderservice.repository.OrdersDetailetRepository;
import org.example.orderservice.repository.OrdersRepository;
import org.example.orderservice.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository orderRepository;
    private final OrdersDetailetRepository orderDetailetRepository;
    private final FoodClient foodClient;
    private final BusinessClient businessClient;

    @Override
    @Transactional
    public void createdOrder(Orders orders) {
        orderRepository.save(orders);
    }

    @Override
    @Transactional
    public void createdOrderDetailet(Orderdetailet orderdetailet) {
        orderDetailetRepository.save(orderdetailet);
    }

    @Override
    public List<Orders> findAllOrders(String userid, Integer orderstate) {
        return orderRepository.findByUseridAndOrderstate(userid, orderstate);
    }

    @Override
    public Orders findOne(Integer orderid) {
        return orderRepository.findById(orderid)
                .orElseThrow(() -> new RuntimeException("订单不存在"));
    }

    @Override
    public List<Orderdetailet> findAllOrderDetail(Integer orderid) {
        return orderDetailetRepository.findByOrderid(orderid);
    }

    @Override
    public Food findFood(Integer foodid) {
        return foodClient.getFoodById(foodid);
    }

    @Override
    public BusinessDTO findBusiness(Integer businessid) {
        return businessClient.getBusinessById(businessid);
    }

    @Override
    @Transactional
    public void updateOrdersState(Integer orderid) {
        orderRepository.updateOrderState(orderid);
    }

    @Override
    @Transactional
    public boolean deleteOrder(Integer orderid) {
        try {
            orderDetailetRepository.deleteByOrderid(orderid);
            int deleted = orderRepository.deleteByOrderid(orderid);
            return deleted > 0;
        } catch (Exception e) {
            throw new RuntimeException("删除订单失败", e);
        }
    }
}