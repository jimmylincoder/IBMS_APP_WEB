package com.suntek.ibms.componet.infinvoa.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 组织信息
 *
 * @author jimmy
 */
public class InfinvoaOrgVo
{
    @JSONField(name = "orgId")
    private String orgId;

    @JSONField(name = "orgCode")
    private String orgCode;

    @JSONField(name = "orgName")
    private String orgName;

    @JSONField(name = "parentId")
    private String parentId;

    public String getOrgId()
    {
        return orgId;
    }

    public void setOrgId(String orgId)
    {
        this.orgId = orgId;
    }

    public String getOrgCode()
    {
        return orgCode;
    }

    public void setOrgCode(String orgCode)
    {
        this.orgCode = orgCode;
    }

    public String getOrgName()
    {
        return orgName;
    }

    public void setOrgName(String orgName)
    {
        this.orgName = orgName;
    }

    public String getParentId()
    {
        return parentId;
    }

    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }
}
