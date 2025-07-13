package org.example.orderservice.repository;

import org.example.orderservice.model.Orderdetailet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface OrdersDetailetRepository extends JpaRepository<Orderdetailet, Integer> {

    // 对应selectAllOrderDetail
    List<Orderdetailet> findByOrderid(Integer orderid);

    // 对应deleteOrderDetails
    @Modifying
    @Transactional
    @Query("DELETE FROM Orderdetailet od WHERE od.orderid = :orderid")
    int deleteByOrderid(@Param("orderid") Integer orderid);
}