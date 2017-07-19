package com.suntek.ibms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 区域
 *
 * @author jimmy
 */
@Entity
@Table(name= "sys_structure_info")
public class Area
{
    //区域id
    @Id
    @Column(name = "ID")
    private String id;

    //区域名称
    @Column(name = "NODE_NAME")
    private String name;

    //排序顺序
    @Column(name = "PARENT_NODE_ID")
    private String parentId;

    //节点层级
    @Column(name = "NODE_LEVEL")
    private String nodeLevel;

    //机构号
    @Column(name = "ORG_CODE")
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
