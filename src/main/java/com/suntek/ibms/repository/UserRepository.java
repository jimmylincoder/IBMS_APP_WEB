package com.suntek.ibms.repository;

import com.suntek.ibms.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户仓库
 *
 * @author jimmy
 */
public interface UserRepository extends JpaRepository<User,String>
{
    /**
     * 根据用户名和密码获取用户信息
     *
     * @param userCode   用户名
     * @param password   密码
     * @return
     */
    User findByUserCodeAndPassword(String userCode,String password);
}
