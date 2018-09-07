package com.suntek.ibms.manager;

import com.suntek.ibms.componet.infinvoa.InfinvoaException;
import com.suntek.ibms.componet.infinvoa.TreeBuilder;
import com.suntek.ibms.componet.infinvoa.vo.InfinvoaCameraResVo;
import com.suntek.ibms.componet.infinvoa.vo.InfinvoaCameraVo;
import com.suntek.ibms.componet.infinvoa.vo.InfinvoaOrgVo;
import com.suntek.ibms.componet.infinvoa.vo.OrgNode;
import com.suntek.ibms.domain.Area;
import com.suntek.ibms.domain.Camera;
import com.suntek.ibms.domain.InfinvoaPlatform;
import com.suntek.ibms.repository.AreaRepository;
import com.suntek.ibms.repository.CameraHistoryRepository;
import com.suntek.ibms.repository.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 英飞拓实体
 */
@Component
public class InfinvoaConvertManager
{
    @Autowired
    private InfinvoaManager infinvoaManager;

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private CameraHistoryRepository cameraHistoryRepository;

    @Autowired
    private AreaRepository areaRepository;

    public void init(InfinvoaPlatform infinvoaPlatform) throws IOException, InfinvoaException
    {
        String ip = infinvoaPlatform.getIp();
        String userName = infinvoaPlatform.getUserName();
        String password = infinvoaPlatform.getPassword();
        cameraHistoryRepository.deleteAll();
        cameraRepository.deleteAll();
        areaRepository.deleteAll();
        initOrg(ip, userName, password);
        initCamera(infinvoaPlatform.getId(), ip, userName, password);
    }

    @Transactional(rollbackFor = Exception.class)
    public void initCamera(String parentId, String ip, String userName, String password) throws IOException, InfinvoaException
    {
        List<Area> areas = areaRepository.findByParentId("0");
        if (areas.size() == 0)
            return;
        String orgId = areas.get(0).getId();
        List<InfinvoaCameraResVo> cameraVos = infinvoaManager.getAllCameraByOrgId(ip, userName, password, orgId);
        List<Camera> cameras = new ArrayList<>();
        for (InfinvoaCameraResVo cameraVo : cameraVos)
        {
            Camera camera = new Camera();
            camera.setId(cameraVo.getResId());
            camera.setName(cameraVo.getResName());
            camera.setAppShow(true);
            camera.setDelStatus(false);
            Map<String, Object> map = cameraVo.getExtend();
            String shareId = (String) map.get("shareId");
            camera.setDeviceId(shareId);
            camera.setIp(ip);
            camera.setIsUsed("1");
            camera.setParentId(parentId);
            camera.setPassword(password);
            camera.setPlace("");
            camera.setPort("80");
            camera.setType("0");
            camera.setVendorName("英飞拓");
            Area area = areaRepository.findOne(cameraVo.getOrgId());
            camera.setArea(area);
            if (area != null)
                cameras.add(camera);
        }
        cameraRepository.save(cameras);
    }


    @Transactional(rollbackFor = Exception.class)
    public void initOrg(String ip, String userName, String password) throws IOException, InfinvoaException
    {
        List<InfinvoaOrgVo> infinvoaOrgVos = infinvoaManager.getAllOrg(ip, userName, password);
        List<Area> areas = new ArrayList<>();
        for (InfinvoaOrgVo infinvoaOrgVo : infinvoaOrgVos)
        {
            Area area = new Area();
            area.setId(infinvoaOrgVo.getOrgId());
            String parentId = infinvoaOrgVo.getParentId();
            area.setParentId(parentId == null ? "0" : infinvoaOrgVo.getParentId());
            area.setNodeLevel(parentId == null ? "1" : null);
            area.setOgrCode(parentId == null ? "01" : null);
            area.setName(infinvoaOrgVo.getOrgName());
            area.setNodeFlag("440301");
            areas.add(area);
        }
        areaRepository.save(areas);
    }


}
