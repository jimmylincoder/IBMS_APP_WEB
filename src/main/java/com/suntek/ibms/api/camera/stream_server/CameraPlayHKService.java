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

    @ParamField(name = "port", checkType = CheckType.NOT_NULL_AND_BLANK, message = "端口号不能为空")
    ThreadLocal<String> port;

    @ParamField(name = "media_channel", checkType = CheckType.NOT_NULL_AND_BLANK, message = "播放通道号")
    ThreadLocal<String> mediaChannel;

    @ParamField(name = "stream_type", checkType = CheckType.NOT_NULL_AND_BLANK, message = "取流形式")
    ThreadLocal<String> streamType;

    @ParamField(name = "device_ip", checkType = CheckType.NOT_NULL_AND_BLANK, message = "设备ip不能为空")
    ThreadLocal<String> deviceIp;

    @ParamField(name = "channel", checkType = CheckType.NOT_NULL_AND_BLANK, message = "通道号不能为空")
    ThreadLocal<String> channel;

    @ParamField(name = "user", checkType = CheckType.NOT_NULL_AND_BLANK, message = "nvr用户名不能为空")
    ThreadLocal<String> user;

    @ParamField(name = "password", checkType = CheckType.NOT_NULL_AND_BLANK, message = "nvr密码不能为空")
    ThreadLocal<String> password;

    @ParamField(name = "begin_time")
    ThreadLocal<String> beginTime;

    @ParamField(name = "end_time")
    ThreadLocal<String> endTime;


    @Override
    public String supportServiceName()
    {
        return "camera.play_hk";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        Map<String, Object> res = cameraControlManager.playByHK(mediaChannel.get(), streamType.get(), deviceIp.get(),
                port.get(), channel.get(), user.get(), password.get(), beginTime.get(), endTime.get());
        return new ResponseBody()
                //.putData("address","rtmp://live.hkstv.hk.lxdns.com/live/hks")
                .putData("session", res.get("session"))
                .putData("address", socketIp)
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
