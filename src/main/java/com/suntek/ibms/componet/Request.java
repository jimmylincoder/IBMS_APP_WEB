package com.suntek.ibms.componet;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Map;

/**
 * Request
 *
 * @author jimmy
 */
public class Request implements Serializable
{
    // 操作系统
    @JSONField(name = "os")
    private String os;

    //服务名
    @JSONField(name = "service")
    private String serviceName;

    @JSONField(name = "udid")
    private String udid;

    // 参数
    @JSONField(name = "params")
    private Map<String, Object> params;

    public Map<String, Object> getParams()
    {
        return params;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }

    public String getOs()
    {
        return os;
    }

    public void setOs(String os)
    {
        this.os = os;
    }

    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public void setUdid(String udid)
    {
        this.udid = udid;
    }

    public String getUdid()
    {
        return udid;
    }
}