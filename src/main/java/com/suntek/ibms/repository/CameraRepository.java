package com.suntek.ibms.repository;

import com.suntek.ibms.domain.Camera;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 摄像机仓库
 *
 * @author jimmy
 */
public interface CameraRepository extends JpaRepository<Camera,String>
{
    /**
     * 通过区域id获取摄像机列表
     *
     * @param orgCode  摄像机id
     * @return
     */
    Page<Camera> findByOrgCode(String orgCode, Pageable pageable);

    /**
     * 通过区域和关键字获取列表
     *
     * @param orgCode
     * @param name
     * @param pageable
     * @return
     */
    Page<Camera> findByOrgCodeAndNameLike(String orgCode,String name,Pageable pageable);
}
