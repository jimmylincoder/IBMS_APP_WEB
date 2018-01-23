package com.suntek.ibms.api.camera.stream_server;

import com.suntek.ibms.componet.Request;
import com.suntek.ibms.componet.Response;
import com.suntek.ibms.componet.ResponseBody;
import com.suntek.ibms.componet.ServiceHandler;
import com.suntek.ibms.componet.annotation.CheckType;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.manager.CameraControlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 改变历史视频的播放速度
 *
 * @author jimmy
 */
@Component
public class CameraChangeSpeedService extends ServiceHandler
{

    @ParamField(name = "session",checkType = CheckType.NOT_NULL_AND_BLANK,message = "session不能为空")
    ThreadLocal<String> session;

    @ParamField(name = "speed",checkType = CheckType.NOT_NULL_AND_BLANK,message = "速度不能为空")
    ThreadLocal<String> speed;

    @Autowired
    CameraControlManager cameraControlManager;

    @Override
    public String supportServiceName()
    {
        return "camera.change_speed";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        cameraControlManager.changeSpeed(session.get(),speed.get());
        return new ResponseBody()
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
