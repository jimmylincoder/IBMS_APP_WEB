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

/**
 * 云台控制接口
 *
 * @author jimmy
 */
@Component
public class CameraPtzService extends ServiceHandler
{
    @Autowired
    CameraControlManager cameraControlManager;

    @ParamField(name = "protocol", checkType = CheckType.NOT_NULL_AND_BLANK, message = "协议不能为空")
    ThreadLocal<String> protocol;

    @ParamField(name = "video_id", checkType = CheckType.NOT_NULL_AND_BLANK, message = "摄像头id不能为空")
    ThreadLocal<String> videoId;

    @ParamField(name = "command", checkType = CheckType.NOT_NULL_AND_BLANK, message = "控制命令不能为空")
    ThreadLocal<String> command;

    @ParamField(name = "speed")
    ThreadLocal<String> speed;

    @ParamField(name = "stop_flag")
    ThreadLocal<String> stopFlag;

    @Override
    public String supportServiceName()
    {
        return "camera.ptz";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        cameraControlManager.ptz(protocol.get(), videoId.get(), command.get(), speed.get(), stopFlag.get());
        return new ResponseBody()
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
