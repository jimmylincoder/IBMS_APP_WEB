package com.suntek.ibms.repository;

import com.suntek.ibms.domain.Camera;
import com.suntek.ibms.domain.CameraHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 摄像头历史仓库
 *
 * @author jimmy
 */
public interface CameraHistoryRepository extends JpaRepository<CameraHistory,String>
{
    CameraHistory findByCameraAndUserCode(Camera camera,String userCode);

    @Query("select cameraHistory from CameraHistory cameraHistory where cameraHistory.camera.delStatus = 0 " +
            "and cameraHistory.camera.appShow = '1' and cameraHistory.userCode = ?1 order by cameraHistory.playTime DESC ")
    Page<CameraHistory> findByUserCodeOrderByPlayTimeDesc(String userCode,Pageable pageable);

    void deleteByCameraAndUserCode(Camera camera,String userCode);
}
