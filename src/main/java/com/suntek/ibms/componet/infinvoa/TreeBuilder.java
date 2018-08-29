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
            if (orgVo.getParentId() == null)
            {
                orgTree.setParentNodeId("-1");
                orgTree.setNodeLevel(1);
            } else
                orgTree.setParentNodeId(orgVo.getParentId());
            orgTree.setNodeName(orgVo.getOrgName());
            orgTree.setOrgCode(orgVo.getOrgCode());
            orgTrees.add(orgTree);
        }
        return orgTrees;
    }

    public List<OrgNode> convert(List<InfinvoaOrgVo> orgVos)
    {
        return tree(convert(orgVos));
    }

    public List<OrgNode> tree(List<OrgNode> orgVos)
    {
        List<OrgNode> nodes = new ArrayList<>();
        Map<String, OrgNode> orgVoMap = new HashMap<>();
        //将列表转成以id为键 机构为值
        for (OrgNode orgVo : orgVos)
            orgVoMap.put(orgVo.getId(), orgVo);

        for (OrgNode orgVo : orgVos)
        {
            //获取父节点
            OrgNode haveParent = orgVoMap.get(orgVo.getParentNodeId());
            //不为空则将此节点加入父节点孩子中,为空则说明为根节点
            if (haveParent != null)
            {
                //判断孩子列表是否为空
                if (haveParent.getChildren() != null)
                {
                    haveParent.getChildren().add(orgVo);
                } else
                {
                    haveParent.setChildren(new ArrayList<>());
                    haveParent.getChildren().add(orgVo);
                }
            } else
            {
                nodes.add(orgVo);
            }
        }
        return orgVos;
    }
}
