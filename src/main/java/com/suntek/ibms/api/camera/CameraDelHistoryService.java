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
 * 删除查看记录
 *
 * @author jimmy
 */
@Component
public class CameraDelHistoryService extends ServiceHandler
{
    @Autowired
    CameraManager cameraManager;

    @ParamField(name = "camera_id",checkType = CheckType.NOT_NULL_AND_BLANK,message = "摄像机id不能为空")
    ThreadLocal<String> cameraId;

    @ParamField(name = "user_code",checkType = CheckType.NOT_NULL_AND_BLANK,message = "用户代码不能为空")
    ThreadLocal<String> userCode;

    @Override
    public String supportServiceName()
    {
        return "camera.del_history";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        cameraManager.delHistory(userCode.get(),cameraId.get());
        return new ResponseBody()
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
