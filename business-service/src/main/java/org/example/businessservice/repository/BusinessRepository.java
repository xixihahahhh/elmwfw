package org.example.businessservice.repository;

import org.example.businessservice.model.Business;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Integer> {

    // 1. 查询所有商家（完全匹配原 selectAll()）
    // 默认方法 findAll() 已满足

    // 2. 首页推荐商家（前5条，匹配原 getIndexBusiness()）
    @Query(value = "SELECT * FROM business ORDER BY businessid LIMIT 5", nativeQuery = true)
    List<Business> findIndexBusiness();

    // 3. 根据点餐分类查询商家（匹配原 getBusinessByType()）
    @Query("SELECT b FROM Business b WHERE b.ordertypeid = :ordertypeid")
    List<Business> findByOrdertypeid(@Param("ordertypeid") Integer ordertypeid);

    // 4. 根据商家ID查询（自动关联 foodList 和 businessType）
    @EntityGraph(attributePaths = {"foodList", "businessType"})
    Business findByBusinessid(Integer businessid);
}