package com.suntek.ibms.manager;

import com.suntek.ibms.vo.VersionVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * app更新管理类
 *
 * @author jimmy
 */
@Service
public class AppVersionManager
{
    @Value("${app.version}")
    private String versionNum;

    @Value("${app.download_address}")
    private String downloadAddress;

    public VersionVo checkVersion(String version)
    {
        VersionVo versionVo = new VersionVo();
        if (versionNum.equals(version))
        {
            versionVo.setIsUpdate("0");
        } else
        {
            versionVo.setIsUpdate("1");
            versionVo.setVersionNum(versionNum);
            versionVo.setDownloadAddress(downloadAddress);
            versionVo.setUpdateContent("修改了部分bug");
        }
        return versionVo;
    }
}
