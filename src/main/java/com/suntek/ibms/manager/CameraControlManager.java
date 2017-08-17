package com.suntek.ibms.manager;

import com.suntek.ibms.componet.HttpClient;
import com.suntek.ibms.componet.MediaHttpEngine;
import com.suntek.ibms.componet.MediaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制c++后台服务
 *
 * @author jimmy
 */
@Component
public class CameraControlManager
{
    @Autowired
    MediaHttpEngine mediaHttpEngine;

    /**
     * 开始播放视频
     *
     * @param deviceId
     * @param deviceIp
     * @param port
     * @param channel
     * @param user
     * @param password
     * @return
     * @throws Exception
     */
    public Map<String,Object> play(String deviceId,String deviceIp,
                                   String port,String channel,
                                   String user,String password) throws Exception
    {
        Map<String,Object> params = new HashMap<>();
        params.put("DeviceID",deviceId);
        params.put("DeviceIP",deviceIp);
        params.put("DevicePort",port);
        params.put("DeviceChn",channel);
        params.put("DeviceUser",user);
        params.put("DevicePass",password);
        params.put("BeginTime","");
        params.put("EndTime","");
        MediaResponse response = mediaHttpEngine.request("play",params);

        return response.getContent();
    }

    /**
     * 停止播放视频
     *
     * @throws Exception
     */
    public void stop() throws Exception
    {
        Map<String,Object> params = new HashMap<>();
        mediaHttpEngine.request("stopPlay",params);
    }
}
