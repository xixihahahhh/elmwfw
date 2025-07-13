package org.example.orderservice.repository;

import org.example.orderservice.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    // 对应selectAllOrders
    List<Orders> findByUseridAndOrderstate(String userid, Integer orderstate);

    // 对应selectOne - JpaRepository已提供findById方法

    // 对应updateOrders
    @Modifying
    @Query("UPDATE Orders o SET o.orderstate = 1 WHERE o.orderid = :orderid")
    @Transactional
    int updateOrderState(@Param("orderid") Integer orderid);

    // 对应deleteOrder
    @Modifying
    @Transactional
    @Query("DELETE FROM Orders o WHERE o.orderid = :orderid")
    int deleteByOrderid(@Param("orderid") Integer orderid);
}