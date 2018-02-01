package com.suntek.ibms.api.camera.stream_server;

import com.suntek.ibms.componet.controller.body.Request;
import com.suntek.ibms.componet.controller.body.Response;
import com.suntek.ibms.componet.controller.body.ResponseBody;
import com.suntek.ibms.componet.base.ServiceHandler;
import com.suntek.ibms.componet.annotation.CheckType;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.manager.CameraControlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 海康sdk播放
 *
 * @author jimmy
 */
@Component
public class CameraPlayHKService extends ServiceHandler
{
    @Value("${camera.socket_ip}")
    String socketIp;

    @Autowired
    CameraControlManager cameraControlManager;

    @ParamField(name = "device_id",checkType = CheckType.NOT_NULL_AND_BLANK,message = "设备id不能为空")
    ThreadLocal<String> deviceId = new ThreadLocal<>();

    @ParamField(name = "media_channel", checkType = CheckType.NOT_NULL_AND_BLANK, message = "播放通道号")
    ThreadLocal<String> mediaChannel = new ThreadLocal<>();

    @ParamField(name = "stream_type", checkType = CheckType.NOT_NULL_AND_BLANK, message = "取流形式")
    ThreadLocal<String> streamType = new ThreadLocal<>();

    @ParamField(name = "begin_time")
    ThreadLocal<String> beginTime = new ThreadLocal<>();

    @ParamField(name = "end_time")
    ThreadLocal<String> endTime = new ThreadLocal<>();


    @Override
    public String supportServiceName()
    {
        return "camera.play_hk";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        Map<String, Object> res = cameraControlManager.playByHK(deviceId.get(),mediaChannel.get(), streamType.get(),
                beginTime.get(), endTime.get());
        return new ResponseBody()
                //.putData("address","rtmp://live.hkstv.hk.lxdns.com/live/hks")
                .putData("session", res.get("session"))
                .putData("address", socketIp)
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
