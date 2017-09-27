package com.suntek.ibms.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 摄像头预览
 *
 * @author jimmy
 */
@Entity
@Table(name = "ibms_app_preview")
public class Preview
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

    //图片地址
    @Column(name = "PREVIEW_PATH")
    private String previewPath;

    //更新时间
    @Column(name = "UPDATE_TIME")
    private long updateTime;

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

    public String getPreviewPath()
    {
        return previewPath;
    }

    public void setPreviewPath(String previewPath)
    {
        this.previewPath = previewPath;
    }

    public long getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(long updateTime)
    {
        this.updateTime = updateTime;
    }
}
