package com.suntek.ibms.api.camera;

import com.suntek.ibms.componet.controller.body.Request;
import com.suntek.ibms.componet.controller.body.Response;
import com.suntek.ibms.componet.controller.body.ResponseBody;
import com.suntek.ibms.componet.base.ServiceHandler;
import com.suntek.ibms.componet.annotation.CheckType;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.manager.CameraManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 添加用户播放历史摄像机
 *
 * @author jimmy
 */
@Component
public class CameraAddHistoryService extends ServiceHandler
{
    @ParamField(name = "camera_id",checkType = CheckType.NOT_NULL_AND_BLANK,message = "摄像机id不能为空")
    ThreadLocal<String> cameraId = new ThreadLocal<>();

    @ParamField(name = "user_code",checkType = CheckType.NOT_NULL_AND_BLANK,message = "用户代码不能为空")
    ThreadLocal<String> userCode = new ThreadLocal<>();

    @Autowired
    CameraManager cameraManager;

    @Override
    public String supportServiceName()
    {
        return "camera.add_history";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        return new ResponseBody()
                .putData("camera",cameraManager.addHistory(userCode.get(),cameraId.get()))
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
