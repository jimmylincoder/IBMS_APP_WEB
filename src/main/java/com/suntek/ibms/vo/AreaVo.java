package com.suntek.ibms.vo;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 区域vo
 *
 * @author jimmy
 */
public class AreaVo implements Serializable
{
    //区域id
    @JSONField(name = "id")
    private String id;

    //区域名称
    @JSONField(name = "name")
    private String name;

    //排序顺序
    @JSONField(name = "parent_id")
    private String parentId;

    //节点层级
    @JSONField(name = "node_level")
    private String nodeLevel;

    //机构号
    @JSONField(name = "org_code")
    private String ogrCode;

    //国际节点编码
    @JSONField(name = "node_flag")
    private String nodeFlag;

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

    public String getNodeFlag()
    {
        return nodeFlag;
    }

    public void setNodeFlag(String nodeFlag)
    {
        this.nodeFlag = nodeFlag;
    }
}
