package org.example.userservice.repository;

import org.example.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * 根据用户ID和密码查询用户（用于登录验证）
     * 对应SQL: SELECT * FROM user WHERE userid = ? AND password = ?
     */
    User findByUseridAndPassword(String userid, String password);

    /**
     * 检查用户ID是否已存在（用于注册验证）
     * 对应SQL: SELECT COUNT(*) > 0 FROM user WHERE userid = ?
     */
    boolean existsByUserid(String userid);

    /**
     * 根据用户ID查询用户信息
     * 对应SQL: SELECT * FROM user WHERE userid = ?
     */
    User findByUserid(String userid);

    /**
     * 根据用户名模糊查询用户列表
     * 对应SQL: SELECT * FROM user WHERE username LIKE %?%
     */
    List<User> findByUsernameContaining(String username);
}