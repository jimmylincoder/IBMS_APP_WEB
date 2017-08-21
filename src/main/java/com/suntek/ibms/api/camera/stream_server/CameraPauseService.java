package com.suntek.ibms.api.camera.stream_server;

import com.suntek.ibms.componet.Request;
import com.suntek.ibms.componet.Response;
import com.suntek.ibms.componet.ResponseBody;
import com.suntek.ibms.componet.ServiceHandler;
import com.suntek.ibms.manager.CameraControlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 暂停播放历史视频
 *
 * @author jimmy
 */
@Component
public class CameraPauseService extends ServiceHandler
{
    @Autowired
    CameraControlManager cameraControlManager;

    @Override
    public String supportServiceName()
    {
        return "camera.pause";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        cameraControlManager.pausePlay();
        return new ResponseBody()
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
