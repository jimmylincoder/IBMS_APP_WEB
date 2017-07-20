package com.suntek.ibms.repository;

import com.suntek.ibms.domain.Camera;
import com.suntek.ibms.domain.CameraHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 摄像头历史仓库
 * g
 * @author jimmy
 */
public interface CameraHistoryRepository extends JpaRepository<CameraHistory,String>
{
    CameraHistory findByCamera(Camera camera);

    Page<CameraHistory> findByOrderByPlayTimeDesc(Pageable pageable);

    void deleteByCamera(Camera camera);
}
