package com.suntek.ibms.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 录像
 *
 * @author jimmy
 */
public class RecordItem implements Serializable
{
    //设备id
    @JSONField(name = "device_id")
    private String deviceId;

    //开始时间
    @JSONField(name = "start_time")
    private long startTime;

    //结束时间
    @JSONField(name = "end_time")
    private long endTime;

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public void setStartTime(long startTime)
    {
        this.startTime = startTime;
    }

    public long getStartTime()
    {
        return startTime;
    }

    public void setEndTime(long endTime)
    {
        this.endTime = endTime;
    }

    public long getEndTime()
    {
        return endTime;
    }
}
