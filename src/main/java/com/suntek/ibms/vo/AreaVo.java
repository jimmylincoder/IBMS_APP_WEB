package com.suntek.ibms.vo;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 区域vo
 *
 * @author jimmy
 */
public class AreaVo
{
    //区域id
    @JSONField(name = "ID")
    private String id;

    //区域名称
    @JSONField(name = "NODE_NAME")
    private String name;

    //排序顺序
    @JSONField(name = "PARENT_NODE_ID")
    private String parentId;

    //节点层级
    @JSONField(name = "NODE_LEVEL")
    private String nodeLevel;

    //机构号
    @JSONField(name = "ORG_CODE")
    private String ogrCode;

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

    public String getParentId()
    {
        return parentId;
    }

    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }

    public String getNodeLevel()
    {
        return nodeLevel;
    }

    public void setNodeLevel(String nodeLevel)
    {
        this.nodeLevel = nodeLevel;
    }

    public String getOgrCode()
    {
        return ogrCode;
    }

    public void setOgrCode(String ogrCode)
    {
        this.ogrCode = ogrCode;
    }
}
