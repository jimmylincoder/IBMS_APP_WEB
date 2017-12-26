package com.suntek.ibms.repository;

import com.suntek.ibms.domain.CameraAreaRel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  摄像机目录关联
 *
 *  @author jimmy
 */
public interface CameraAreaRelRepository extends JpaRepository<CameraAreaRel,String>
{
    Page<CameraAreaRel> findByStructureNodeFlag(String structureNodeFlag, Pageable pageable);
}
