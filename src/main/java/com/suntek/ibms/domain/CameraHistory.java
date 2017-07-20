package com.suntek.ibms.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 摄像机历史播放表
 *
 * @author jimmy
 */
@Entity
@Table(name = "ibms_app_camera_history")
public class CameraHistory
{
    //主id
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    //摄像头id
    @Column(name = "CAMERA_ID")
    private String cameraId;

    //播放时间
    @Column(name = "PLAY_TIME")
    private long playTime;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getCameraId()
    {
        return cameraId;
    }

    public void setCameraId(String cameraId)
    {
        this.cameraId = cameraId;
    }

    public long getPlayTime()
    {
        return playTime;
    }

    public void setPlayTime(long playTime)
    {
        this.playTime = playTime;
    }
}
