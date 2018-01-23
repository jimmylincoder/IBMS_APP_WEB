package com.suntek.ibms.manager;

import com.suntek.ibms.domain.Area;
import com.suntek.ibms.domain.User;
import com.suntek.ibms.repository.AreaRepository;
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

    @Autowired
    AreaRepository areaRepository;

    public List<UserVo> getUserInfo()
    {
        List<User> users = userRepository.findAll();
        List<UserVo> userVos = new ArrayList<>();
        for (User user : users)
        {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            userVos.add(userVo);
        }
        return userVos;
    }

    /**
     * 用户登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    public UserVo login(String userName, String password)
    {
        UserVo userVo = new UserVo();
        User user = userRepository.findByUserCodeAndPassword(userName, DESCrypt.md5(password));
        if (user == null)
        {
            return null;
        }
        BeanUtils.copyProperties(user, userVo);
        String deptCode = user.getDeptCode();
        Area area = areaRepository.findByOgrCode(deptCode);
        userVo.setDeptName(area.getName());
        return userVo;
    }

    /**
     * 修改密码
     *
     * @param userCode
     * @param newPassword
     * @param oldPassword
     * @throws Exception
     */
    public void changePassword(String userCode, String newPassword, String oldPassword) throws Exception
    {
        User user = userRepository.findByUserCodeAndPassword(userCode, DESCrypt.md5(oldPassword));
        if (newPassword.equals(oldPassword))
        {
            throw new Exception("旧密码和新密码一致");
        }
        if (user == null)
        {
            throw new Exception("旧密码输入不正确");
        }
        userRepository.updateUserPassword(userCode, DESCrypt.md5(newPassword));
    }
}
