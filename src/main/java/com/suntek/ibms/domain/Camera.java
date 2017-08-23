package com.suntek.ibms.domain;

import javax.persistence.*;

/**
 * 摄像机实体
 *
 * @author jimmy
 */
@Entity
@Table(name= "videopointinfo")
public class Camera
{
    @Id
    @Column(name = "VIDEOID")
    private String id;

    @Column(name = "VIDEONAME")
    private String name;

    @Column(name = "VIDEOFLAG")
    private String deviceId;

    @Column(name = "SERVERFLAG")
    private String parentId;

    @Column(name = "BASETYPE")
    private String type;

    @Column(name = "IPLACE")
    private String place;

    @Column(name = "SCHANNEL")
    private String channel;

//    @Column(name = "ORG_CODE")
//    private String orgCode;

    @Column(name = "SERVERIP")
    private String ip;

    @Column(name = "PORT")
    private String port;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "VENDOR_NAME")
    private String vendorName;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, optional = true)
    @JoinColumn(name = "ORG_CODE",nullable = false)
    private Area area;

    @Column(name = "DELETE_STATUS")
    private boolean delStatus;

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

    public void setArea(Area area)
    {
        this.area = area;
    }

    public Area getArea()
    {
        return area;
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

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }

    public String getParentId()
    {
        return parentId;
    }

    public void setVendorName(String vendorName)
    {
        this.vendorName = vendorName;
    }

    public String getVendorName()
    {
        return vendorName;
    }

    public void setDelStatus(boolean delStatus)
    {
        this.delStatus = delStatus;
    }

    public boolean isDelStatus()
    {
        return delStatus;
    }
}
