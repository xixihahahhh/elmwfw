package org.example.businessservice.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foodid")
    private Integer foodid;

    @Column(name = "foodname")
    private String foodname;

    @Column(name = "foodprice")
    private BigDecimal foodprice;

    // 关联商家（多对一）
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "businessid")
    private Business business;

    // getters/setters
}