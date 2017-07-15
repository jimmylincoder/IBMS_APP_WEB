package com.suntek.ibms.repository;

import com.suntek.ibms.domain.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 摄像机仓库
 *
 * @author jimmy
 */
public interface CameraRepository extends JpaRepository<Camera,String>
{
}
