package com.suntek.ibms.manager;

import com.suntek.ibms.domain.Camera;
import com.suntek.ibms.repository.CameraRepository;
import com.suntek.ibms.vo.CameraVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 摄像机服务
 *
 * @author jimmy
 */
@Service
public class CameraManager
{
    @Autowired
    CameraRepository cameraRepository;

    /**
     * 获取摄像机列表
     *
     * @return
     */
    public List<CameraVo> getCameraList()
    {
        List<Camera> cameras = cameraRepository.findAll();
        List<CameraVo> cameraVos = new ArrayList<>();
        for (Camera camera : cameras)
        {
            CameraVo cameraVo = new CameraVo();
            BeanUtils.copyProperties(camera,cameraVo);
            cameraVos.add(cameraVo);
        }
        return cameraVos;
    }

    /**
     * 根据id获取摄像机信息
     *
     * @param cameraId
     * @return
     */
    public CameraVo getOne(String cameraId)
    {
        Camera camera = cameraRepository.findOne(cameraId);
        CameraVo cameraVo = new CameraVo();
        BeanUtils.copyProperties(camera,cameraVo);
        return cameraVo;
    }
}
