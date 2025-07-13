package org.example.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.elmcommon.model.Food;
import org.example.orderservice.dto.BusinessDTO;
import org.example.orderservice.model.Orderdetailet;
import org.example.orderservice.model.Orders;
import org.example.orderservice.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrdersService ordersService;

    // 1. 创建订单
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Integer> createOrder(@RequestBody Orders order) {
        ordersService.createdOrder(order);
        return ResponseEntity.ok(order.getOrderid());
    }

    // 2. 添加订单明细
    @PostMapping("/details")
    public ResponseEntity<Void> addOrderDetail(@RequestBody Orderdetailet detail) {
        ordersService.createdOrderDetailet(detail);
        return ResponseEntity.ok().build();
    }

    // 3. 获取用户订单列表
    @GetMapping("/user/{userid}")
    public ResponseEntity<List<Orders>> getUserOrders(
            @PathVariable String userid,
            @RequestParam(required = false) Integer orderstate) {
        List<Orders> orders = orderstate != null ?
                ordersService.findAllOrders(userid, orderstate) :
                ordersService.findAllOrders(userid, null);
        return ResponseEntity.ok(orders);
    }

    // 4. 获取订单详情
    @GetMapping("/{orderid}")
    public ResponseEntity<Orders> getOrderDetails(@PathVariable Integer orderid) {
        Orders order = ordersService.findOne(orderid);
        return ResponseEntity.ok(order);
    }

    // 5. 获取订单明细列表
    @GetMapping("/{orderid}/details")
    public ResponseEntity<List<Orderdetailet>> getOrderItems(@PathVariable Integer orderid) {
        List<Orderdetailet> details = ordersService.findAllOrderDetail(orderid);
        return ResponseEntity.ok(details);
    }

    // 6. 获取订单关联食品信息
    @GetMapping("/foods/{foodid}")
    public ResponseEntity<Food> getFoodInfo(@PathVariable Integer foodid) {
        Food food = ordersService.findFood(foodid);
        return ResponseEntity.ok(food);
    }

    // 7. 获取订单关联商家信息
    @GetMapping("/business/{businessid}")
    public ResponseEntity<BusinessDTO> getBusinessInfo(@PathVariable Integer businessid) {
        BusinessDTO business = ordersService.findBusiness(businessid);
        return ResponseEntity.ok(business);
    }

    // 8. 更新订单状态
    @PatchMapping("/{orderid}/status")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Integer orderid) {
        ordersService.updateOrdersState(orderid);
        return ResponseEntity.ok().build();
    }

    // 9. 删除订单
    @DeleteMapping("/{orderid}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderid) {
        boolean deleted = ordersService.deleteOrder(orderid);
        return deleted ? ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    // 统一异常处理（可放在@ControllerAdvice中）
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());
    }
}