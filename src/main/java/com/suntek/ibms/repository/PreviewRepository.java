package com.suntek.ibms.repository;

import com.suntek.ibms.domain.Preview;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 预览
 */
public interface PreviewRepository extends JpaRepository<Preview, String>
{
    Preview findByCameraId(String cameraId);
}
