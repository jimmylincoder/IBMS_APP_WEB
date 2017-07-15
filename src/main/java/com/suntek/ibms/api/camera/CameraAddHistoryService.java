package com.suntek.ibms.api.camera;

import com.suntek.ibms.componet.Request;
import com.suntek.ibms.componet.Response;
import com.suntek.ibms.componet.ServiceHandler;
import org.springframework.stereotype.Component;

/**
 * 添加用户播放历史摄像机
 *
 * @author jimmy
 */
@Component
public class CameraAddHistoryService implements ServiceHandler
{
    @Override
    public String supportServiceName()
    {
        return "camera.add_history";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        return null;
    }
}
