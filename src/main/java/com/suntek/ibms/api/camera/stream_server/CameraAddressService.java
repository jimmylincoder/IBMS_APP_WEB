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

import java.util.Map;


/**
 * 获取视频播放地址
 *
 * @author jimmy
 */
@Component
public class CameraAddressService extends ServiceHandler
{
    @ParamField(name = "device_id",checkType = CheckType.NOT_NULL_AND_BLANK,message = "设备id不能为空")
    String deviceId;

    @ParamField(name = "parent_id",checkType = CheckType.NOT_NULL_AND_BLANK,message = "nvr id不能为空")
    String parentId;

    @ParamField(name = "device_ip",checkType = CheckType.NOT_NULL_AND_BLANK,message = "设备ip不能为空")
    String deviceIp;

    @ParamField(name = "channel",checkType = CheckType.NOT_NULL_AND_BLANK,message = "通道号不能为空")
    String channel;

    @ParamField(name = "user",checkType = CheckType.NOT_NULL_AND_BLANK,message = "nvr用户名不能为空")
    String user;

    @ParamField(name = "password",checkType = CheckType.NOT_NULL_AND_BLANK,message = "nvr密码不能为空")
    String password;

    @ParamField(name = "begin_time")
    String beginTime;

    @ParamField(name = "end_time")
    String endTime;

    @Autowired
    CameraControlManager cameraControlManager;

    @Override
    public String supportServiceName()
    {
        return "camera.play";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        Map<String,Object> res = cameraControlManager.play(deviceId,parentId, deviceIp, channel, user, password,beginTime,endTime);
        return new ResponseBody()
                //.putData("address","rtmp://live.hkstv.hk.lxdns.com/live/hks")
                .putData("session",res.get("session"))
                .putData("address", res.get("PlayUrl"))
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
