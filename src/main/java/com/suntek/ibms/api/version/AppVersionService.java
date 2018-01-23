package com.suntek.ibms.api.version;

import com.suntek.ibms.componet.controller.body.Request;
import com.suntek.ibms.componet.controller.body.Response;
import com.suntek.ibms.componet.controller.body.ResponseBody;
import com.suntek.ibms.componet.base.ServiceHandler;
import com.suntek.ibms.componet.annotation.CheckType;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.manager.AppVersionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 版本检测更新
 *
 * @author jimmy
 */
@Component
public class AppVersionService extends ServiceHandler
{
    @Autowired
    AppVersionManager appVersionManager;

    @ParamField(name = "version_num", checkType = CheckType.NOT_NULL_AND_BLANK, message = "版本号不能为空")
    ThreadLocal<String> versionNum;

    @Override
    public String supportServiceName()
    {
        return "app.version";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        return new ResponseBody()
                .putData("version", appVersionManager.checkVersion(versionNum.get()))
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
