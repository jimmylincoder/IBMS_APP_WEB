package com.suntek.ibms.api.camera.stream_server;

import com.suntek.ibms.componet.Request;
import com.suntek.ibms.componet.Response;
import com.suntek.ibms.componet.ResponseBody;
import com.suntek.ibms.componet.ServiceHandler;
import com.suntek.ibms.manager.CameraControlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 恢复播放历史视频
 *
 * @author jimmy
 */
@Component
public class CameraResumeService extends ServiceHandler
{

    @Autowired
    CameraControlManager cameraControlManager;

    @Override
    public String supportServiceName()
    {
        return "camera.resume";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        cameraControlManager.resumePlay();
        return new ResponseBody()
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
