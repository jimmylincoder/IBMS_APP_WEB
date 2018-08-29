package com.suntek.ibms.manager;

import com.suntek.ibms.componet.infinvoa.InfinvoaException;
import com.suntek.ibms.componet.infinvoa.TreeBuilder;
import com.suntek.ibms.componet.infinvoa.vo.InfinvoaCameraVo;
import com.suntek.ibms.componet.infinvoa.vo.InfinvoaOrgVo;
import com.suntek.ibms.componet.infinvoa.vo.OrgNode;
import com.suntek.ibms.domain.Area;
import com.suntek.ibms.domain.Camera;
import com.suntek.ibms.repository.AreaRepository;
import com.suntek.ibms.repository.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
    private AreaRepository areaRepository;

    @Autowired
    private TreeBuilder treeBuilder;

    public void init(String ip, String userName, String password) throws IOException, InfinvoaException
    {
        initCamera(ip, userName, password);
    }

    public void initCamera(String ip, String userName, String password) throws IOException, InfinvoaException
    {
        List<InfinvoaCameraVo> cameraVos = infinvoaManager.getAllCamera(ip, userName, password);
        List<Camera> cameras = new ArrayList<>();
        for (InfinvoaCameraVo cameraVo : cameraVos)
        {
            Camera camera = new Camera();
            camera.setId(cameraVo.getCameraId());
            camera.setName(cameraVo.getCameraName());
            camera.setAppShow(true);
            camera.setDelStatus(false);
            camera.setDeviceId(cameraVo.getSourceShareId());
            camera.setIp(ip);
            camera.setIsUsed("1");
            camera.setParentId(cameraVo.getDeviceId());
            camera.setPassword(password);
            camera.setPlace("");
            camera.setPort("80");
            camera.setType("0");
            camera.setVendorName("英飞拓");
            Area area = areaRepository.findByOgrCode("0101");
            camera.setArea(area);
            cameras.add(camera);
        }
        cameraRepository.save(cameras);
    }
}
