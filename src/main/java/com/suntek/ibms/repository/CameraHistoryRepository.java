package com.suntek.ibms.repository;

import com.suntek.ibms.domain.CameraHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 摄像头历史仓库
 * g
 * @author jimmy
 */
public interface CameraHistoryRepository extends JpaRepository<CameraHistory,String>
{
    CameraHistory findByCameraId(String cameraId);
}
