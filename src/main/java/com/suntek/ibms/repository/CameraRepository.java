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
     * @param areaId  摄像机id
     * @return
     */
    @Query("select camera from Camera camera where camera.area.id like ?1% and camera.delStatus = 0 and camera.appShow = 1 Order by camera.isUsed desc")
    Page<Camera> findByOrgCode(String areaId, Pageable pageable);

    /**
     * 通过关键字获取列表
     *
     * @param name
     * @param pageable
     * @return
     */
    @Query("select camera from Camera camera where camera.name like %?1% and camera.delStatus = 0 and camera.appShow = 1")
    Page<Camera> findByNameLike(String name,Pageable pageable);


    @Query("select camera from Camera camera where camera.delStatus = 0 and camera.appShow = 1")
    Page<Camera> findAllDelStatusAndAppShow(Pageable pageable);

    /**
     * 查询对应id的分页摄像机列表
     *
     * @param cameraId
     * @param pageable
     * @return
     */
    Page<Camera> findByIdIn(List<String> cameraId,Pageable pageable);

    @Query("select camera from Camera camera where camera.deviceId in ?1 and camera.delStatus = 0 and camera.appShow = 1")
    Page<Camera> findByDeviceIdIn(List<String> deviceIds,Pageable pageable);

    @Query("select camera from Camera camera where camera.deviceId = ?1 and camera.appShow = 1")
    Camera findByDeviceId(String deviceId);
}
