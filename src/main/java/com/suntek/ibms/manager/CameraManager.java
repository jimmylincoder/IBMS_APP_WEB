package com.suntek.ibms.manager;

import com.suntek.ibms.domain.*;
import com.suntek.ibms.exception.CameraException;
import com.suntek.ibms.repository.*;
import com.suntek.ibms.util.Base64Img;
import com.suntek.ibms.vo.CameraVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
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

    @Value("${preview.root_path}")
    String previewRootPath;

    @Autowired
    CameraRepository cameraRepository;

    @Autowired
    CameraHistoryRepository cameraHistoryRepository;

    @Autowired
    CameraAreaRelRepository cameraAreaRelRepository;

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    UserRepository userRepository;


    public Page<CameraVo> getCameraList0(String areaId, int page)
    {
        Page<Camera> cameraPage = null;
        Page<CameraVo> cameraVoPage = null;
        List<Area> areas = areaRepository.findByParentId("0");
        if (areas.size() == 0)
            return new PageImpl<CameraVo>(new ArrayList<>());
        String rootId = areas.get(0).getId();
        Pageable pageable = new PageRequest(page - 1, PAGE_SIZE);
        if (areaId == null || "".equals(areaId) || rootId.equals(areaId))
        {
            cameraPage = cameraRepository.findAllDelStatusAndAppShow(pageable);
        } else if (areaId != null || !"".equals(areaId))
        {
            cameraPage = cameraRepository.findAllDelStatusAndAppShowAndOrgId(areaId, pageable);
        }
        cameraVoPage = cameraPage.map(new Converter<Camera, CameraVo>()
        {
            @Override
            public CameraVo convert(Camera camera)
            {
                return convertToVo(camera);
            }
        });
        return cameraVoPage;
    }

    /**
     * 获取摄像机列表
     *
     * @return
     */
    public Page<CameraVo> getCameraList(String areaId, int page)
    {
        Page<Camera> cameraPage = null;
        List<CameraAreaRel> cameraAreaRelPage = null;
        Page<CameraVo> cameraVoPage = null;
        List<String> deviceIds = new ArrayList<>();
        Pageable pageable = new PageRequest(page - 1, PAGE_SIZE);
        if (areaId == null || "".equals(areaId) || "01".equals(areaId))
        {
            cameraPage = cameraRepository.findAllDelStatusAndAppShow(pageable);
            cameraVoPage = cameraPage.map(new Converter<Camera, CameraVo>()
            {
                @Override
                public CameraVo convert(Camera camera)
                {
                    return convertToVo(camera);
                }
            });
        } else if (areaId != null || !"".equals(areaId))
        {
            List<Area> areas = areaRepository.findByOgrCode(areaId);
            if (areas.size() != 0)
                cameraAreaRelPage = cameraAreaRelRepository.findByStructureNodeFlag(areas.get(0).getNodeFlag());
            for (CameraAreaRel cameraAreaRel : cameraAreaRelPage)
            {
                deviceIds.add(cameraAreaRel.getDeviceFlag());
            }
            if (deviceIds.size() > 0)
            {
                cameraPage = cameraRepository.findByDeviceIdIn(deviceIds, pageable);
                cameraVoPage = cameraPage.map(new Converter<Camera, CameraVo>()
                {
                    @Override
                    public CameraVo convert(Camera camera)
                    {
                        return convertToVo(camera);
                    }
                });
            } else
            {
                List<CameraVo> cameraVos = new ArrayList<>();
                cameraVoPage = new PageImpl<CameraVo>(cameraVos);
            }
        }

        return cameraVoPage;
    }

    /**
     * 通过关键字获取摄像机列表
     *
     * @param keyword
     * @param page
     * @return
     */
    public Page<CameraVo> getCameraListByKeyword(String keyword, int page)
    {
        Page<Camera> cameraPage = cameraRepository.findByNameLike(keyword, new PageRequest(page - 1, PAGE_SIZE));
        Page<CameraVo> cameraVoPage = cameraPage.map(new Converter<Camera, CameraVo>()
        {
            @Override
            public CameraVo convert(Camera camera)
            {
                return convertToVo(camera);
            }
        });
        return cameraVoPage;
    }

    private CameraVo convertToVo(Camera camera)
    {
        CameraVo cameraVo = new CameraVo();
        cameraVo.setOrgCode(camera.getArea().getOgrCode());
        cameraVo.setOrgName(camera.getArea().getName());
        BeanUtils.copyProperties(camera, cameraVo);
        return cameraVo;
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
            BeanUtils.copyProperties(camera, cameraVo);
            cameraVo.setOrgCode(camera.getArea().getOgrCode());
            cameraVo.setOrgName(camera.getArea().getName());
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
        return convertToVo(camera);
    }

    /**
     * 获取播放记录列表
     *
     * @param page
     * @return
     */
    public Page<CameraVo> getHistory(String userCode, int page)
    {
        Page<CameraHistory> cameraHistoryPage = cameraHistoryRepository.findByUserCodeOrderByPlayTimeDesc(userCode,
                new PageRequest(page - 1, PAGE_SIZE));
        Page<CameraVo> cameraVoPage = cameraHistoryPage.map(new Converter<CameraHistory, CameraVo>()
        {
            @Override
            public CameraVo convert(CameraHistory cameraHistory)
            {
                Camera camera = cameraHistory.getCamera();
                CameraVo cameraVo = convertToVo(camera);
                cameraVo.setPlayTime(cameraHistory.getPlayTime());
                cameraVo.setPlayCount(cameraHistory.getPlayCount() + "");
                return cameraVo;
            }
        });
        return cameraVoPage;
    }


    /**
     * 添加摄像头查看记录
     *
     * @return
     */
    public CameraVo addHistory(String userCode, String cameraId) throws CameraException
    {
        if (!cameraRepository.exists(cameraId))
            throw new CameraException("该摄像头不存在");

        List<User> users = userRepository.findByUserCode(userCode);
        if (users.size() == 0)
            throw new CameraException("该用户不存在");

        //查询是否已存在该摄像头信息，不存在更插入，存在则更新时间
        Camera camera = new Camera();
        camera.setId(cameraId);
        long playTime = new Date().getTime();
        CameraHistory cameraHistory = cameraHistoryRepository.findByCameraAndUserCode(camera, userCode);
        if (cameraHistory == null)
        {
            cameraHistory = new CameraHistory();
            cameraHistory.setCamera(camera);
            cameraHistory.setPlayTime(playTime);
            cameraHistory.setUserCode(userCode);
            cameraHistory.setPlayCount(1);
        } else
        {
            cameraHistory.setPlayTime(playTime);
            int count = cameraHistory.getPlayCount();
            cameraHistory.setPlayCount(++count);
        }
        cameraHistoryRepository.save(cameraHistory);

        //获取该摄像头信息
        Camera cameraResult = cameraRepository.findOne(cameraId);
        CameraVo cameraVo = new CameraVo();
        BeanUtils.copyProperties(cameraResult, cameraVo);
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
    public void delHistory(String userCode, String cameraId) throws CameraException
    {
        if (!cameraRepository.exists(cameraId))
            throw new CameraException("该摄像头不存在");

        if (!userRepository.exists(userCode))
            throw new CameraException("该用户不存在");
        Camera camera = new Camera();
        camera.setId(cameraId);
        cameraHistoryRepository.deleteByCameraAndUserCode(camera, userCode);

    }

    public Page<CameraVo> findAll(int page, int pageSize)
    {
        Page<Camera> cameraPage = cameraRepository.findAll(new PageRequest(page - 1, pageSize));
        Page<CameraVo> cameraVoPage = cameraPage.map(new Converter<Camera, CameraVo>()
        {
            @Override
            public CameraVo convert(Camera camera)
            {
                return convertToVo(camera);
            }
        });
        return cameraVoPage;
    }

    public void setAppShow(String id) throws CameraException
    {
        Camera camera = cameraRepository.findOne(id);
        if (camera == null)
            throw new CameraException("摄像机不存在");
        camera.setAppShow(!camera.isAppShow());
        cameraRepository.save(camera);
    }
}
