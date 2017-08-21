package com.suntek.ibms.api.camera.stream_server;

import com.suntek.ibms.componet.Request;
import com.suntek.ibms.componet.Response;
import com.suntek.ibms.componet.ResponseBody;
import com.suntek.ibms.componet.ServiceHandler;
import com.suntek.ibms.manager.CameraControlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 录像时间段
 *
 * @author jimmy
 */
@Component
public class CameraRecordService extends ServiceHandler
{
    @Autowired
    CameraControlManager cameraControlManager;

    @Override
    public String supportServiceName()
    {
        return "camera.record";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        Map<String,Object> map = cameraControlManager.record();
        return new ResponseBody()
                .putAll(map)
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
