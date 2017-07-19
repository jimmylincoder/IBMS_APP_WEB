package com.suntek.ibms.repository;

import com.suntek.ibms.domain.Camera;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
     * 通过关键字获取列表
     *
     * @param name
     * @param pageable
     * @return
     */
    @Query("select camera from Camera camera where camera.name like %?1%")
    Page<Camera> findByNameLike(String name,Pageable pageable);
}
