package com.suntek.ibms.componet.infinvoa.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Map;

/**
 * 摄像头信息
 *
 * @author jimmy
 */
public class InfinvoaCameraResVo
{
    @JSONField(name = "orgId")
    private String orgId;

    @JSONField(name = "resId")
    private String resId;

    @JSONField(name = "resType")
    private String resType;

    @JSONField(name = "resName")
    private String resName;

    @JSONField(name = "enable")
    private boolean enalbe;

    @JSONField(name = "createTime")
    private String createTime;

    @JSONField(name = "extend")
    private Map<String, Object> extend;

    public String getOrgId()
    {
        return orgId;
    }

    public void setOrgId(String orgId)
    {
        this.orgId = orgId;
    }

    public String getResId()
    {
        return resId;
    }

    public void setResId(String resId)
    {
        this.resId = resId;
    }

    public String getResType()
    {
        return resType;
    }

    public void setResType(String resType)
    {
        this.resType = resType;
    }

    public String getResName()
    {
        return resName;
    }

    public void setResName(String resName)
    {
        this.resName = resName;
    }

    public boolean isEnalbe()
    {
        return enalbe;
    }

    public void setEnalbe(boolean enalbe)
    {
        this.enalbe = enalbe;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public Map<String, Object> getExtend()
    {
        return extend;
    }

    public void setExtend(Map<String, Object> extend)
    {
        this.extend = extend;
    }
}
