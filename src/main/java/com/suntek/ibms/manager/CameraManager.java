package com.suntek.ibms.manager;

import com.suntek.ibms.domain.Camera;
import com.suntek.ibms.domain.CameraHistory;
import com.suntek.ibms.repository.CameraHistoryRepository;
import com.suntek.ibms.repository.CameraRepository;
import com.suntek.ibms.vo.CameraVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    CameraHistoryRepository cameraHistoryRepository;

    /**
     * 获取摄像机列表
     *
     * @return
     */
    public Page<CameraVo> getCameraList(String areaId,int page)
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
        Page<CameraVo> cameraVoPage = cameraPage.map(new Converter<Camera, CameraVo>()
        {
            @Override
            public CameraVo convert(Camera camera)
            {
                CameraVo cameraVo = new CameraVo();
                BeanUtils.copyProperties(camera,cameraVo);
                return cameraVo;
            }
        });
        return cameraVoPage;
    }

    /**
     *  通过关键字获取摄像机列表
     *
     * @param keyword
     * @param page
     * @return
     */
    public Page<CameraVo> getCameraListByKeyword(String keyword,int page)
    {
        Page<Camera> cameraPage = cameraRepository.findByNameLike(keyword,new PageRequest(page - 1,PAGE_SIZE));
        Page<CameraVo> cameraVoPage = cameraPage.map(new Converter<Camera, CameraVo>()
        {
            @Override
            public CameraVo convert(Camera camera)
            {
                CameraVo cameraVo = new CameraVo();
                BeanUtils.copyProperties(camera,cameraVo);
                return cameraVo;
            }
        });
        return cameraVoPage;
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

    /**
     * 获取播放记录列表
     *
     * @param page
     * @return
     */
    public Page<CameraVo> getHistory(int page)
    {
        Page<CameraHistory> cameraHistoryPage = cameraHistoryRepository.findByOrderByPlayTimeDesc(new PageRequest(page -1,PAGE_SIZE));
        Page<CameraVo> cameraVoPage = cameraHistoryPage.map(new Converter<CameraHistory, CameraVo>()
        {
            @Override
            public CameraVo convert(CameraHistory cameraHistory)
            {
                Camera camera = cameraHistory.getCamera();
                CameraVo cameraVo = new CameraVo();
                BeanUtils.copyProperties(camera,cameraVo);
                cameraVo.setPlayTime(cameraHistory.getPlayTime());
                return cameraVo;
            }
        });
        return  cameraVoPage;
    }


    /**
     * 添加摄像头查看记录
     *
     * @return
     */
    public CameraVo addHistory(String cameraId) throws Exception
    {
        if(!cameraRepository.exists(cameraId))
            throw new Exception("该摄像头不存在");

        //查询是否已存在该摄像头信息，不存在更插入，存在则更新时间
        Camera camera = new Camera();
        camera.setId(cameraId);
        long playTime = new Date().getTime();
        CameraHistory cameraHistory = cameraHistoryRepository.findByCamera(camera);
        if(cameraHistory == null)
        {
            cameraHistory = new CameraHistory();
            cameraHistory.setCamera(camera);
            cameraHistory.setPlayTime(playTime);
        }else
        {
            cameraHistory.setPlayTime(playTime);
        }
        cameraHistoryRepository.save(cameraHistory);

        //获取该摄像头信息
        Camera cameraResult = cameraRepository.findOne(cameraId);
        CameraVo cameraVo = new CameraVo();
        BeanUtils.copyProperties(cameraResult,cameraVo);
        cameraVo.setPlayTime(playTime);
        return cameraVo;
    }

    /**
     * 删除记录
     *
     * @param cameraId
     */
    @Transactional
    @Modifying
    public void delHistory(String cameraId) throws Exception
    {
        if(!cameraRepository.exists(cameraId))
            throw new Exception("该摄像头不存在");
        try
        {
            Camera camera = new Camera();
            camera.setId(cameraId);
            cameraHistoryRepository.deleteByCamera(camera);
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }
}
