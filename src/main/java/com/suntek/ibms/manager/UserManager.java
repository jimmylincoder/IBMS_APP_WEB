package com.suntek.ibms.manager;

import com.suntek.ibms.domain.User;
import com.suntek.ibms.repository.UserRepository;
import com.suntek.ibms.util.DESCrypt;
import com.suntek.ibms.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户服务
 *
 * @author jimmy
 */
@Service
@Validated
public class UserManager
{
    @Autowired
    UserRepository userRepository;

    public List<UserVo> getUserInfo()
    {
        List<User> users = userRepository.findAll();
        List<UserVo> userVos = new ArrayList<>();
        for(User user : users)
        {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user,userVo);
            userVos.add(userVo);
        }
        return userVos;
    }

    /**
     * 用户登录
     *
     * @param userName  用户名
     * @param password  密码
     * @return
     */
    public UserVo login(String userName,String password)
    {
        UserVo userVo = new UserVo();
        User user = userRepository.findByUserCodeAndPassword(userName, DESCrypt.md5(password));
        if(user == null)
        {
            return null;
        }
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }
}
