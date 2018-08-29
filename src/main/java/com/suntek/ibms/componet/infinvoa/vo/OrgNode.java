package com.suntek.ibms.componet.infinvoa.vo;

import java.util.List;

/**
 * 组织架构树
 *
 * @author jimmy
 */
public class OrgNode
{
    private String id;

    private String nodeName;

    private int nodeLevel;

    private String orgCode;

    private String parentNodeId;

    private boolean isLeaf;

    private List<OrgNode> children;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getNodeName()
    {
        return nodeName;
    }

    public void setNodeName(String nodeName)
    {
        this.nodeName = nodeName;
    }

    public int getNodeLevel()
    {
        return nodeLevel;
    }

    public void setNodeLevel(int nodeLevel)
    {
        this.nodeLevel = nodeLevel;
    }

    public String getOrgCode()
    {
        return orgCode;
    }

    public void setOrgCode(String orgCode)
    {
        this.orgCode = orgCode;
    }

    public String getParentNodeId()
    {
        return parentNodeId;
    }

    public void setParentNodeId(String parentNodeId)
    {
        this.parentNodeId = parentNodeId;
    }

    public boolean isLeaf()
    {
        return isLeaf;
    }

    public void setLeaf(boolean leaf)
    {
        isLeaf = leaf;
    }

    public void setChildren(List<OrgNode> children)
    {
        this.children = children;
    }

    public List<OrgNode> getChildren()
    {
        return children;
    }
}
