package com.suntek.ibms.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 版本信息
 *
 * @author jimmy
 */
public class VersionVo implements Serializable
{
    @JSONField(name = "is_update")
    private String isUpdate;

    @JSONField(name = "version_num")
    private String versionNum;

    @JSONField(name = "update_context")
    private String updateContent;

    @JSONField(name = "download_address")
    private String downloadAddress;

    public String getVersionNum()
    {
        return versionNum;
    }

    public void setVersionNum(String versionNum)
    {
        this.versionNum = versionNum;
    }

    public String getUpdateContent()
    {
        return updateContent;
    }

    public void setUpdateContent(String updateContent)
    {
        this.updateContent = updateContent;
    }

    public void setDownloadAddress(String downloadAddress)
    {
        this.downloadAddress = downloadAddress;
    }

    public String getDownloadAddress()
    {
        return downloadAddress;
    }

    public void setIsUpdate(String isUpdate)
    {
        this.isUpdate = isUpdate;
    }

    public String getIsUpdate()
    {
        return isUpdate;
    }
}
