package com.suntek.ibms.api.camera;

import com.suntek.ibms.componet.Request;
import com.suntek.ibms.componet.Response;
import com.suntek.ibms.componet.ServiceHandler;
import org.springframework.stereotype.Component;

/**
 * 获取历史播放摄像机列表
 *
 * @author jimmy
 */
@Component
public class CameraHistoryListService extends ServiceHandler
{
    @Override
    public String supportServiceName()
    {
        return "camera.history_list";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        return null;
    }
}
