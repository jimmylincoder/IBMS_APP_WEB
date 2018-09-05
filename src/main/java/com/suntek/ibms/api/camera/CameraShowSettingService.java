package com.suntek.ibms.api.camera;

import com.suntek.ibms.componet.annotation.CheckType;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.componet.base.ServiceHandler;
import com.suntek.ibms.componet.controller.body.Request;
import com.suntek.ibms.componet.controller.body.Response;
import com.suntek.ibms.componet.controller.body.ResponseBody;
import com.suntek.ibms.manager.CameraManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 摄像机显示设备
 *
 * @author jimmy
 */
@Component
public class CameraShowSettingService extends ServiceHandler
{
    @Autowired
    private CameraManager cameraManager;

    @ParamField(name = "id",checkType = CheckType.NOT_NULL_AND_BLANK,message = "id不能为空")
    ThreadLocal<String> id = new ThreadLocal<>();

    @Override
    public String supportServiceName()
    {
        return "camera.app_show";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        cameraManager.setAppShow(id.get());
        return new ResponseBody()
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
