package com.suntek.ibms.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.xml.ws.Action;

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
    @JoinColumn(name = "CAMERA_ID",nullable = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private Camera camera;

    //播放时间
    @Column(name = "PLAY_TIME")
    private long playTime;

    //用户代码
    @Column(name = "USER_CODE")
    private String userCode;

    //播放次数
    @Column(name = "PLAY_COUNT")
    private int playCount;

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

    public String getUserCode()
    {
        return userCode;
    }

    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }

    public int getPlayCount()
    {
        return playCount;
    }

    public void setPlayCount(int playCount)
    {
        this.playCount = playCount;
    }
}
