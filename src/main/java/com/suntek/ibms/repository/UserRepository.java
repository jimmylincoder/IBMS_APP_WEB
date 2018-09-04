package com.suntek.ibms.repository;

import com.suntek.ibms.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户仓库
 *
 * @author jimmy
 */
public interface UserRepository extends JpaRepository<User, String>
{
    /**
     * 根据用户名和密码获取用户信息
     *
     * @param userCode 用户名
     * @param password 密码
     * @return
     */
    User findByUserCodeAndPassword(String userCode, String password);

    @Transactional
    @Modifying
    @Query("update User user set user.password = ?2 where user.userCode = ?1")
    int updateUserPassword(String userCode, String password);

    List<User> findByUserCode(String userName);

    void deleteByUserCode(String userCode);
}
