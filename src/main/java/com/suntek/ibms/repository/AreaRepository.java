package com.suntek.ibms.repository;

import com.suntek.ibms.domain.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *  区域仓库
 *
 *  @author jimmy
 */
public interface AreaRepository extends JpaRepository<Area,String>
{
    /**
     * 根据父id查询列表
     *
     * @param parentId 父id
     * @return
     */
    List<Area> findByParentId(String parentId);

    /**
     * 根据节点码获取区域
     *
     * @param orgCode
     * @return
     */
    Area findByOgrCode(String orgCode);

    Area findByNodeFlag(String nodeFlag);
}
