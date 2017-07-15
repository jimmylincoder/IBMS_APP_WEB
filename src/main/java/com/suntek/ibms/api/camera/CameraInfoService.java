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
 * 获取摄像头信息
 *
 * @author jimmy
 */
@Component
public class CameraInfoService implements ServiceHandler
{
    @Autowired
    CameraManager cameraManager;

    @Override
    public String supportServiceName()
    {
        return "camera.getCameraInfo";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        Response response = new Response();
        Map<String,Object> params = request.getParams();
        Map<String,Object> content = new HashMap<>();
        String cameraId = (String) params.get("camera_id");

        if(cameraId == null || cameraId.equals(""))
        {
            response.setStatus(Response.STATUS_FAILURE);
            response.setErrorMessage("摄像机id不能为空");
            return response;
        }

        content.put("camera", cameraManager.getOne(cameraId));
        response.setContent(content);
        response.setStatus(Response.STATUS_SUCCESS);

        return response;
    }
}
