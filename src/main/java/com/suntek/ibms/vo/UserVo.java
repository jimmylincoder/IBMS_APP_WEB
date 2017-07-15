package com.suntek.ibms.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 *  用户值对象
 *
 *  @author jimmy
 *
 **/
public class UserVo implements Serializable
{
    @JSONField(name = "user_code")
    private String userCode;

    @JSONField(name = "user_name")
    private String userName;

    @JSONField(name = "password")
    private String password;

    public String getUserCode()
    {
        return userCode;
    }

    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
