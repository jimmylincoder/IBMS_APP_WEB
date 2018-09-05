package com.suntek.ibms.componet.infinvoa;

import com.suntek.ibms.componet.infinvoa.vo.InfinvoaOrgVo;
import com.suntek.ibms.componet.infinvoa.vo.OrgNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 树型构建器
 *
 * @author jimmy
 */
@Component
public class TreeBuilder
{
    /**
     * 转换成节点形式
     *
     * @param orgVos
     * @return
     */
    public List<OrgNode> convertToTree(List<InfinvoaOrgVo> orgVos)
    {
        List<OrgNode> orgTrees = new ArrayList<>();
        for (InfinvoaOrgVo orgVo : orgVos)
        {
            OrgNode orgTree = new OrgNode();
            orgTree.setId(orgVo.getOrgId());
            orgTree.setNodeName(orgVo.getOrgName());
            orgTree.setOrgCode(orgVo.getOrgCode());
            if (orgVo.getParentId() == null)
            {
                orgTree.setParentNodeId("-1");
                orgTree.setNodeLevel(1);
            } else
                orgTree.setParentNodeId(orgVo.getParentId());
            orgTrees.add(orgTree);
        }
        return orgTrees;
    }

    public OrgNode convert(List<InfinvoaOrgVo> orgVos)
    {
        List<OrgNode> orgNodes = convertToTree(orgVos);
        return tree(getRoot(orgNodes), removeRoot(orgNodes));
    }

    public OrgNode getRoot(List<OrgNode> orgVos)
    {
        OrgNode root = new OrgNode();
        for (OrgNode orgNode : orgVos)
        {
            if ("-1".equals(orgNode.getParentNodeId()))
            {
                root = orgNode;
                break;
            }
        }
        return root;
    }

    public List<OrgNode> removeRoot(List<OrgNode> orgNodes)
    {
        OrgNode root = new OrgNode();
        for(OrgNode orgNode : orgNodes)
        {
            if ("-1".equals(orgNode.getParentNodeId()))
            {
                root = orgNode;
                break;
            }
        }
        orgNodes.remove(root);
        return orgNodes;
    }

    public OrgNode tree(OrgNode node, List<OrgNode> orgVos)
    {
        for (OrgNode orgNode : orgVos)
        {
            if (orgNode.getParentNodeId().equals(node.getId()))
            {
                List<OrgNode> childrend = node.getChildren();
                if(childrend == null)
                    childrend = new ArrayList<>();
                childrend.add(orgNode);
                node.setChildren(childrend);
                tree(orgNode, orgVos);
            }
        }
        return node;
    }
}
