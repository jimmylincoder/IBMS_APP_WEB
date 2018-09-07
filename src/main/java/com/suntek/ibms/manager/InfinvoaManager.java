package com.suntek.ibms.manager;

import com.alibaba.fastjson.JSON;
import com.suntek.ibms.componet.infinvoa.InfinvoaEngine;
import com.suntek.ibms.componet.infinvoa.InfinvoaException;
import com.suntek.ibms.componet.infinvoa.InfinvoaResponse;
import com.suntek.ibms.componet.infinvoa.vo.InfinvoaCameraResVo;
import com.suntek.ibms.componet.infinvoa.vo.InfinvoaCameraVo;
import com.suntek.ibms.componet.infinvoa.vo.InfinvoaOrgVo;
import com.suntek.ibms.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.util.*;

/**
 * 英飞拓平台业务类
 *
 * @author jimmy
 */
@Component
public class InfinvoaManager
{
    @Autowired
    private InfinvoaEngine infinvoaEngine;

    public String login(String ip, String userName, String password) throws IOException, InfinvoaException
    {
        Map<String, Object> params = new HashMap<>();
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Basic " + Base64.getEncoder().encodeToString((userName + ":" +
                DigestUtils.md5DigestAsHex(password.getBytes()).toUpperCase()).getBytes()));
        InfinvoaResponse response = infinvoaEngine.requestByGet(ip, "/CMS/main/login.do", header, params);
        String cookie = response.getCookie() + "; userInfo.userName=" + userName + "; userInfo.loginName=" + userName
                + "; userInfo.originalLoginPassword=" + userName + "; userInfo.loginFlag=1; userInfo.password=";
        return cookie;
    }

    public List<InfinvoaOrgVo> getAllOrg(String ip, String userName, String password) throws IOException, InfinvoaException
    {
        String cookie = login(ip, userName, password);
        Map<String, Object> params = new HashMap<>();
        Map<String, String> header = new HashMap<>();
        header.put("Cookie", cookie);
        InfinvoaResponse response = infinvoaEngine.requestByGet(ip, "/CMS/action/topology/org/getAll.do", header, params);
        List<Map<String, Object>> result = (List<Map<String, Object>>) response.getMsg();
        List<InfinvoaOrgVo> orgVos = new ArrayList<>();
        for (Map<String, Object> map : result)
        {
            String str = JSON.toJSONString(map);
            InfinvoaOrgVo orgVo = JSON.parseObject(str, InfinvoaOrgVo.class);
            orgVos.add(orgVo);
        }
        return orgVos;
    }

    public List<InfinvoaCameraResVo> getAllCameraByOrgId(String ip, String userName, String password, String orgId) throws IOException, InfinvoaException
    {
        String cookie = login(ip, userName, password);
        Map<String, Object> params = new HashMap<>();
        Map<String, String> header = new HashMap<>();
        params.put("orgId", orgId);
        params.put("types", "camera");
        params.put("recursion", "true");
        header.put("Cookie", cookie);
        InfinvoaResponse response = infinvoaEngine.requestByGet(ip, "/CMS/action/topology/resourcezonning/getByResourceTypes.do", header, params);
        List<Map<String, Object>> result = (List<Map<String, Object>>) response.getMsg();
        List<InfinvoaCameraResVo> cameraResVos = new ArrayList<>();
        for (Map<String, Object> map : result)
        {
            String str = JSON.toJSONString(map);
            InfinvoaCameraResVo cameraResVo = JSON.parseObject(str, InfinvoaCameraResVo.class);
            cameraResVos.add(cameraResVo);
        }
        return cameraResVos;
    }


    public List<InfinvoaCameraVo> getAllCamera(String ip, String userName, String password) throws IOException, InfinvoaException
    {
        String cookie = login(ip, userName, password);
        Map<String, Object> params = new HashMap<>();
        Map<String, String> header = new HashMap<>();
        header.put("Cookie", cookie);
        InfinvoaResponse response = infinvoaEngine.requestByGet(ip, "/CMS/action/device/camera/getAll.do", header, params);
        List<Map<String, Object>> result = (List<Map<String, Object>>) response.getMsg();
        List<InfinvoaCameraVo> cameraVos = new ArrayList<>();
        for (Map<String, Object> map : result)
        {
            String str = JSON.toJSONString(map);
            InfinvoaCameraVo cameraVo = JSON.parseObject(str, InfinvoaCameraVo.class);
            cameraVos.add(cameraVo);
        }
        return cameraVos;
    }
}
