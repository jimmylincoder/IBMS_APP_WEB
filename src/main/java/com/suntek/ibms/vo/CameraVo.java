package com.suntek.ibms.vo;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

/**
 * 摄像机值对像
 *
 * @author jimmy
 */
public class CameraVo implements Serializable
{
    @JSONField(name = "id")
    private String id;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "type")
    private String type;

    @JSONField(name = "place")
    private String place;

    @JSONField(name = "channel")
    private String channel;

    @JSONField(name = "org_code")
    private String orgCode;

    @JSONField(name = "ip")
    private String ip;

    @JSONField(name = "port")
    private String port;

    @JSONField(name = "user_name")
    private String userName;

    @JSONField(name = "password")
    private String password;

    @JSONField(name = "play_time")
    private long playTime;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getPlace()
    {
        return place;
    }

    public void setPlace(String place)
    {
        this.place = place;
    }

    public String getChannel()
    {
        return channel;
    }

    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    public String getOrgCode()
    {
        return orgCode;
    }

    public void setOrgCode(String orgCode)
    {
        this.orgCode = orgCode;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getPort()
    {
        return port;
    }

    public void setPort(String port)
    {
        this.port = port;
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

    public long getPlayTime()
    {
        return playTime;
    }

    public void setPlayTime(long playTime)
    {
        this.playTime = playTime;
    }
}
