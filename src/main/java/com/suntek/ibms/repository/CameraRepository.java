package com.suntek.ibms.repository;

import com.suntek.ibms.domain.Area;
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
     * @param areaId  摄像机id
     * @return
     */
    @Query("select camera from Camera camera where camera.area.id like ?1% and camera.delStatus = 0 Order by camera.isUsed")
    Page<Camera> findByOrgCode(String areaId, Pageable pageable);

    /**
     * 通过关键字获取列表
     *
     * @param name
     * @param pageable
     * @return
     */
    @Query("select camera from Camera camera where camera.name like %?1% and camera.delStatus = 0")
    Page<Camera> findByNameLike(String name,Pageable pageable);


    @Query("select camera from Camera camera where camera.delStatus = 0")
    Page<Camera> findAll(Pageable pageable);

    /**
     * 查询对应id的分页摄像机列表
     *
     * @param cameraId
     * @param pageable
     * @return
     */
    Page<Camera> findByIdIn(List<String> cameraId,Pageable pageable);
}
