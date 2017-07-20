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
    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, optional = true)
    @JoinColumn(name = "CAMERA_ID",nullable = false)
    private Camera camera;

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

    public Camera getCamera()
    {
        return camera;
    }

    public void setCamera(Camera camera)
    {
        this.camera = camera;
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
