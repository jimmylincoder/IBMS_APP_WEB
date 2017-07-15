package com.suntek.ibms.api.camera;

import com.suntek.ibms.componet.Request;
import com.suntek.ibms.componet.Response;
import com.suntek.ibms.componet.ServiceHandler;
import com.suntek.ibms.manager.CameraManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取摄像机列表
 *
 * @author jimmy
 */
@Component
public class CameraListService extends ServiceHandler
{
    @Autowired
    CameraManager cameraManager;

    @Override
    public String supportServiceName()
    {
        return "camera.list";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        return responseBody
                .putData("camera_list",cameraManager.getCameraList())
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
