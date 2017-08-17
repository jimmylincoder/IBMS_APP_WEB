package com.suntek.ibms.api.camera;

import com.suntek.ibms.componet.Request;
import com.suntek.ibms.componet.Response;
import com.suntek.ibms.componet.ResponseBody;
import com.suntek.ibms.componet.ServiceHandler;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.manager.CameraControlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 获取视频播放地址
 *
 * @author jimmy
 */
@Component
public class CameraAddressService extends ServiceHandler
{
    @ParamField(name = "deviceId")
    String deviceId;

    @ParamField(name = "deviceIp")
    String deviceIp;

    @ParamField(name = "port")
    String port;

    @ParamField(name = "channel")
    String channel;

    @ParamField(name = "user")
    String user;

    @ParamField(name = "password")
    String password;

    @Autowired
    CameraControlManager cameraControlManager;

    @Override
    public String supportServiceName()
    {
        return "camera.address";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        return new ResponseBody()
                //            .putData("address","rtmp://live.hkstv.hk.lxdns.com/live/hks")
                .putData("address", cameraControlManager.play(deviceId, deviceIp, port, channel, user, password))
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
