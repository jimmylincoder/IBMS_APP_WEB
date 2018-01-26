package com.suntek.ibms.api.camera.stream_server;

import com.suntek.ibms.componet.controller.body.Request;
import com.suntek.ibms.componet.controller.body.Response;
import com.suntek.ibms.componet.controller.body.ResponseBody;
import com.suntek.ibms.componet.base.ServiceHandler;
import com.suntek.ibms.componet.annotation.CheckType;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.manager.CameraControlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 查询进度
 *
 * @author jimmy
 */
@Component
public class CameraQueryProgressService extends ServiceHandler
{
    @ParamField(name = "session",checkType = CheckType.NOT_NULL_AND_BLANK,message = "session不能为空")
    ThreadLocal<String> session = new ThreadLocal<>();

    @Autowired
    CameraControlManager cameraControlManager;

    @Override
    public String supportServiceName()
    {
        return "camera.query_progress";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        Map<String,Object> map = cameraControlManager.queryProgress(session.get());
        return new ResponseBody()
                .setStatus(Response.STATUS_SUCCESS)
                .putData("progress",map.get("progress"))
                .bulid();
    }
}
