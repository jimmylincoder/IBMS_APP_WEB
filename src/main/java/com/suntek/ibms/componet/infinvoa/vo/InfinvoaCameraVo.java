package com.suntek.ibms.componet.infinvoa.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 英飞拓报像机信息
 *
 * @author jimmy
 */
public class InfinvoaCameraVo
{
    @JSONField(name = "cameraId")
    private String cameraId;

    @JSONField(name = "cameraName")
    private String cameraName;

    @JSONField(name = "channelInfo")
    private String channelInfo;

    @JSONField(name = "sourceShareId")
    private String sourceShareId;

    @JSONField(name = "deviceId")
    private String deviceId;

    public String getCameraId()
    {
        return cameraId;
    }

    public void setCameraId(String cameraId)
    {
        this.cameraId = cameraId;
    }

    public String getCameraName()
    {
        return cameraName;
    }

    public void setCameraName(String cameraName)
    {
        this.cameraName = cameraName;
    }

    public String getChannelInfo()
    {
        return channelInfo;
    }

    public void setChannelInfo(String channelInfo)
    {
        this.channelInfo = channelInfo;
    }

    public String getSourceShareId()
    {
        return sourceShareId;
    }

    public void setSourceShareId(String sourceShareId)
    {
        this.sourceShareId = sourceShareId;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }
}
