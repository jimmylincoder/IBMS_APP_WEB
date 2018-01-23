package com.suntek.ibms.manager;


import com.suntek.ibms.componet.media.MediaHttpEngine;
import com.suntek.ibms.componet.media.MediaResponse;
import com.suntek.ibms.domain.Camera;
import com.suntek.ibms.repository.CameraRepository;
import com.suntek.ibms.vo.RecordItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 控制c++后台服务
 * HTTP接口：移动终端->MobileServer
 * const std::string REQUEST_PLAY	= "/mobileservice/play";   //请求播放实时和历史视频
 * const std::string REQUEST_STOP_PLAY	= "/mobileservice/stopplay";   //请求停止播放实时和历史视频
 * const std::string REQUEST_CHANGE_SPEED = "/mobileservice/changespeed";   //改变历史视频的播放速度
 * const std::string REQUEST_CHANGE_PLAY_POSITION = "/mobileservice/changeplayposition";   //改变历史视频的播放位置
 * const std::string REQUEST_PAUSE_PLAY	= "/mobileservice/pauseplay";   //暂停播放历史视频
 * const std::string REQUEST_RESUME_PLAY	= "/mobileservice/resumeplay";   //恢复播放历史视频
 * const std::string REQUEST_QUERY_RECORD_FILE = "/mobileservice/queryrecordfile"; //查询录像
 *
 * @author jimmy
 */
@Component
public class CameraControlManager
{
    @Autowired
    MediaHttpEngine mediaHttpEngine;

    @Autowired
    CameraRepository cameraRepository;

    @Value("${camera.nvr_port}")
    String nvrPort;

    /**
     * 开始播放视频(实时或历史)
     * 历史则加上开始时间和结束时间字段
     *
     * @param deviceId
     * @param deviceIp
     * @param channel
     * @param user
     * @param password
     * @return
     * @throws Exception
     */
    public Map<String, Object> playByGB28181(String deviceId, String parentId, String deviceIp,
                                             String channel, String user,
                                             String password, String beginTime,
                                             String endTime) throws Exception
    {
        Map<String, Object> params = new HashMap<>();
        beginTime = beginTime == null ? "" : timeStrToTStr(beginTime);
        endTime = endTime == null ? "" : timeStrToTStr(endTime);

        params.put("DeviceID", deviceId);
        params.put("DeviceIP", deviceIp);
        params.put("ParentID", parentId);
        if (deviceIp.equals("172.16.16.179"))
            params.put("DevicePort", "5061");
        else
            params.put("DevicePort", nvrPort);
        params.put("DeviceChn", channel);
        params.put("DeviceUser", user);
        params.put("DevicePass", password);
        params.put("BeginTime", beginTime);
        params.put("EndTime", endTime);
        params.put("Protocol", "GB28181");
        MediaResponse response = mediaHttpEngine.request("play", params);
        Map<String, Object> res = response.getContent();
        res.put("session", response.getSession());

        return res;
    }

    /**
     * 停止播放视频
     *
     * @param session 播放视频返回的session
     * @throws Exception
     */
    public void stop(String session) throws Exception
    {
        Map<String, Object> params = new HashMap<>();
        params.put("session", session);
        mediaHttpEngine.request("stopplay", params);
    }

    /**
     * 改变历史视频的播放速度
     *
     * @throws Exception
     */
    public void changeSpeed(String session, String speed) throws Exception
    {
        Map<String, Object> params = new HashMap<>();
        params.put("session", session);
        params.put("Speed", speed);
        mediaHttpEngine.request("changespeed", params);
    }

    /**
     * 改变历史视频的播放位置
     *
     * @param session
     * @throws Exception
     */
    public void changePlayPosition(String session, String position) throws Exception
    {
        Map<String, Object> params = new HashMap<>();
        params.put("session", session);
        params.put("position", position);
        mediaHttpEngine.request("changeplayposition", params);
    }

    /**
     * 暂停播放历史视频
     *
     * @throws Exception
     */
    public void pausePlay(String session) throws Exception
    {
        Map<String, Object> params = new HashMap<>();
        params.put("session", session);
        mediaHttpEngine.request("pauseplay", params);
    }

    /**
     * 恢复播放历史视频
     *
     * @throws Exception
     */
    public void resumePlay(String session) throws Exception
    {
        Map<String, Object> params = new HashMap<>();
        params.put("session", session);
        mediaHttpEngine.request("resumeplay", params);
    }


    /**
     * 查询录像
     *
     * @throws Exception
     */
    public List<RecordItem> queryRecordFile(String deviceId, String parentId, String deviceIp,
                                            String channel, String user,
                                            String password, String beginTime,
                                            String endTime, String protocol) throws Exception
    {
        Map<String, Object> params = new HashMap<>();
        params.put("DeviceID", deviceId);
        params.put("ParentID", parentId);
        params.put("DeviceIP", deviceIp);
        if ("GB28181".equals(protocol))
        {
            if (deviceIp.equals("172.16.16.179"))
                params.put("DevicePort", "5061");
            else
                params.put("DevicePort", nvrPort);
        } else if ("Hikvision".equals(protocol))
        {
            params.put("DevicePort", 8000);
        }
        params.put("DeviceChn", channel);
        params.put("DeviceUser", user);
        params.put("DevicePass", password);
        params.put("BeginTime", timeStrToTStr(beginTime));
        params.put("EndTime", timeStrToTStr(endTime));
        params.put("Protocol", protocol);
        MediaResponse response = mediaHttpEngine.request("queryrecordfile", params);
        List<RecordItem> records = new ArrayList<>();
        List<Map<String, Object>> items = (List<Map<String, Object>>) ((Map) response.getContent().get("RecordList")).get("Item");
        if (items != null)
        {
            for (Map item : items)
            {
                String id = (String) item.get("DeviceID");
                String startTime = (String) item.get("StartTime");
                String endTime1 = (String) item.get("EndTime");

                RecordItem recordItem = new RecordItem();
                recordItem.setDeviceId(id);
                recordItem.setStartTime(timeStrToLong(startTime));
                recordItem.setEndTime(timeStrToLong(endTime1));

                records.add(recordItem);
            }
        }
        return records;
    }

    public Map<String, Object> queryProgress(String session) throws Exception
    {
        Map<String, Object> map = new HashMap<>();
        map.put("session", session);

        MediaResponse response = mediaHttpEngine.request("queryplayprogress", map);
        return response.getContent();
    }

    /**
     * 将2017-07-27T09:18:14格式时间转为时间戳
     *
     * @param str
     * @return
     * @throws ParseException
     */
    private long timeStrToLong(String str) throws ParseException
    {
        String[] strs = str.split("T");
        String date = strs[0];
        String time = strs[1];

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = format.parse(date + " " + time);
        return date1.getTime();
    }

    /**
     * 将2017-07-27 09:18:14格式时间转为2017-07-27T09:18:14
     *
     * @param str
     * @return
     */
    private String timeStrToTStr(String str)
    {
        String[] strs = str.split(" ");
        return strs[0] + "T" + strs[1];
    }

    /**
     * 海康形式
     *
     * @param mediaChannel 手机端播放通道
     * @param streamType   取流形式
     * @param deviceIp     设备ip
     * @param channel      通道号
     * @param user         用户名
     * @param password     密码
     * @param beginTime    开始时间
     * @param endTime      播放时间
     * @return
     * @throws Exception
     */
    public Map<String, Object> playByHK(String mediaChannel, String streamType, String deviceIp, String port,
                                        String channel, String user, String password, String beginTime, String endTime) throws Exception
    {
        Map<String, Object> params = new HashMap<>();
        beginTime = beginTime == null ? "" : timeStrToTStr(beginTime);
        endTime = endTime == null ? "" : timeStrToTStr(endTime);

        params.put("Protocol", "Hikvision");
        params.put("DeviceIP", deviceIp);
        params.put("MediaChannel", mediaChannel);
        params.put("StreamType", streamType);
        params.put("DevicePort", port);
        params.put("DeviceChn", channel);
        params.put("DeviceUser", user);
        params.put("DevicePass", password);
        params.put("BeginTime", beginTime);
        params.put("EndTime", endTime);
        MediaResponse response = mediaHttpEngine.request("play", params);
        Map<String, Object> res = response.getContent();
        res.put("session", response.getSession());

        return res;
    }

    /**
     * 云台操作
     *
     * @param protocol
     * @param videoId
     * @param command
     * @param speed
     * @param stopFlag
     */
    public Map<String, Object> ptz(String protocol, String videoId, String command, String speed, String stopFlag) throws Exception
    {
        Camera camera = cameraRepository.findOne(videoId);
        Map<String, Object> params = new HashMap<>();

        params.put("Protocol", protocol);
        params.put("DeviceID", camera.getDeviceId());
        params.put("DeviceIP", camera.getIp());
        params.put("DevicePort", camera.getPort());
        params.put("DeviceChn", camera.getChannel());
        params.put("DeviceUser", camera.getUserName());
        params.put("DevicePass", camera.getPassword());
        params.put("Command", command);
        params.put("Speed", speed);
        params.put("StopFlag", stopFlag);

        MediaResponse response = mediaHttpEngine.request("ptzcontrol", params);
        Map<String, Object> res = response.getContent();
        res.put("session", response.getSession());

        return res;
    }
}
