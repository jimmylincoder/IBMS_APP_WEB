package com.suntek.ibms.manager;

import com.suntek.ibms.vo.AreaVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 区域管理类
 *
 * @author jimmy
 */
@Service
public class AreaManager
{
    private final String ROOT_ID = "01";

    /**
     * 通过父id获取区域列表
     *
     * @return  区域列表
     */
    public List<AreaVo> getAreaListByParentId(String parentId)
    {
        List<AreaVo> areaVos = new ArrayList<>();

        return areaVos;
    }

    /**
     * 获取根结点下的区域列表
     *
     * @return 区域列表
     */
    public List<AreaVo> getAreaListByRootId()
    {
        return getAreaListByParentId(ROOT_ID);
    }
}
