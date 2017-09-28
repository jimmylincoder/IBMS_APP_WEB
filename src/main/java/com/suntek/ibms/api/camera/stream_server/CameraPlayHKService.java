package com.suntek.ibms.api.camera.stream_server;

import com.suntek.ibms.componet.Request;
import com.suntek.ibms.componet.Response;
import com.suntek.ibms.componet.ResponseBody;
import com.suntek.ibms.componet.ServiceHandler;
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

    @ParamField(name = "media_channel", checkType = CheckType.NOT_NULL_AND_BLANK, message = "播放通道号")
    String mediaChannel;

    @ParamField(name = "stream_type", checkType = CheckType.NOT_NULL_AND_BLANK, message = "取流形式")
    String streamType;

    @ParamField(name = "device_ip", checkType = CheckType.NOT_NULL_AND_BLANK, message = "设备ip不能为空")
    String deviceIp;

    @ParamField(name = "channel", checkType = CheckType.NOT_NULL_AND_BLANK, message = "通道号不能为空")
    String channel;

    @ParamField(name = "user", checkType = CheckType.NOT_NULL_AND_BLANK, message = "nvr用户名不能为空")
    String user;

    @ParamField(name = "password", checkType = CheckType.NOT_NULL_AND_BLANK, message = "nvr密码不能为空")
    String password;

    @ParamField(name = "begin_time")
    String beginTime;

    @ParamField(name = "end_time")
    String endTime;


    @Override
    public String supportServiceName()
    {
        return "camera.play_hk";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        Map<String, Object> res = cameraControlManager.playByHK(mediaChannel, streamType, deviceIp, channel,
                user, password, beginTime, endTime);
        return new ResponseBody()
                //.putData("address","rtmp://live.hkstv.hk.lxdns.com/live/hks")
                .putData("session", res.get("session"))
                .putData("address", socketIp)
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
