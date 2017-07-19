package com.suntek.ibms.manager;

import com.suntek.ibms.domain.Area;
import com.suntek.ibms.repository.AreaRepository;
import com.suntek.ibms.vo.AreaVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    AreaRepository areaRepository;

    private final String ROOT_ID = "1";

    /**
     * 通过父id获取区域列表
     *
     * @return 区域列表
     */
    public List<AreaVo> getAreaListByParentId(String parentId)
    {
        List<AreaVo> areaVos = new ArrayList<>();
        List<Area> areas = areaRepository.findByParentId(parentId);
        for (Area area : areas)
        {
            AreaVo areaVo = new AreaVo();
            BeanUtils.copyProperties(area, areaVo);
            areaVos.add(areaVo);
        }
        return areaVos;
    }

    /**
     * 获取根结点下的区域列表
     *
     * @return 区域列表
     */
    public List<AreaVo> getAreaListByRootId()
    {
        List<AreaVo> areaVos = new ArrayList<>();
        List<Area> areas = areaRepository.findByParentId(ROOT_ID);
        for (Area area : areas)
        {
            AreaVo areaVo = new AreaVo();
            BeanUtils.copyProperties(area, areaVo);
            areaVos.add(areaVo);
        }
        return areaVos;
    }
}
