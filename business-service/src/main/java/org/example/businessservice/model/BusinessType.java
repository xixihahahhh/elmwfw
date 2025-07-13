package org.example.businessservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "business_type")
public class BusinessType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "typeid")
    private Integer typeid;

    @Column(name = "typename")
    private String typename;

    // 关联商家（一对多）
    @OneToMany(mappedBy = "businessType")
    private List<Business> businesses;

    // getters/setters
    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}