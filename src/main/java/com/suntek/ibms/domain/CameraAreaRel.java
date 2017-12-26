package com.suntek.ibms.domain;

import javax.persistence.*;

/**
 *
 * 摄像机关联区域表
 *
 * @author jimmy
 *
 */
@Entity
@Table(name = "structure_device_rel")
public class CameraAreaRel
{
    @Id
    @Column(name = "DEVICEFLAG")
    private String deviceFlag;

    @Column(name = "STRUCTURE_NODE_FLAG")
    private String structureNodeFlag;

    @Column(name = "DEVICEID")
    private String deviceId;

    @Column(name = "DEVICETYPE")
    private String deviceType;

    @Column(name = "ORG_CODE")
    private String orgCode;

    @Column(name = "PLATFORM_FLAG")
    private String platformFlag;

    public String getDeviceFlag()
    {
        return deviceFlag;
    }

    public void setDeviceFlag(String deviceFlag)
    {
        this.deviceFlag = deviceFlag;
    }

    public String getStructureNodeFlag()
    {
        return structureNodeFlag;
    }

    public void setStructureNodeFlag(String structureNodeFlag)
    {
        this.structureNodeFlag = structureNodeFlag;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public String getDeviceType()
    {
        return deviceType;
    }

    public void setDeviceType(String deviceType)
    {
        this.deviceType = deviceType;
    }

    public String getOrgCode()
    {
        return orgCode;
    }

    public void setOrgCode(String orgCode)
    {
        this.orgCode = orgCode;
    }

    public String getPlatformFlag()
    {
        return platformFlag;
    }

    public void setPlatformFlag(String platformFlag)
    {
        this.platformFlag = platformFlag;
    }
}
