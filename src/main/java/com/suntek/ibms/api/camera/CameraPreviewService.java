package com.suntek.ibms.api.camera;

import com.suntek.ibms.componet.controller.body.Request;
import com.suntek.ibms.componet.controller.body.Response;
import com.suntek.ibms.componet.base.ServiceHandler;
import org.springframework.stereotype.Component;

/**
 * 摄像机预览图片添加
 *
 * @author jimmy
 */
@Component
public class CameraPreviewService extends ServiceHandler
{
    @Override
    public String supportServiceName()
    {
        return "camera.add_preview";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        return null;
    }
}
