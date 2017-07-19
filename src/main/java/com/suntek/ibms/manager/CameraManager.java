package com.suntek.ibms.manager;

import com.suntek.ibms.domain.Camera;
import com.suntek.ibms.repository.CameraRepository;
import com.suntek.ibms.vo.CameraVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    //每页页数
    private final int PAGE_SIZE = 15;

    @Autowired
    CameraRepository cameraRepository;

    /**
     * 获取摄像机列表
     *
     * @return
     */
    public List<CameraVo> getCameraList(String areaId,int page)
    {
        Page<Camera> cameraPage = null;
        Pageable pageable = new PageRequest(page - 1,PAGE_SIZE);
        if(areaId == null || "".equals(""))
        {
            cameraPage = cameraRepository.findAll(pageable);
        }
        else if(areaId != null || !"".equals(areaId))
        {
            cameraPage = cameraRepository.findByOrgCode(areaId,pageable);
        }
        List<Camera> cameras = cameraPage.getContent();
        List<CameraVo> cameraVos = poConvertVo(cameras);
        return cameraVos;
    }

    /**
     *  通过关键字获取摄像机列表
     *
     * @param keyword
     * @param page
     * @return
     */
    public List<CameraVo> getCameraListByKeyword(String keyword,int page)
    {
        Page<Camera> cameraPage = cameraRepository.findByNameLike(keyword,new PageRequest(page - 1,PAGE_SIZE));
        List<Camera> cameras = cameraPage.getContent();
        List<CameraVo> cameraVos = poConvertVo(cameras);
        return cameraVos;
    }

    /**
     * 将摄像机po转成vo
     *
     * @param cameras
     * @return
     */
    private List<CameraVo> poConvertVo(List<Camera> cameras)
    {
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
