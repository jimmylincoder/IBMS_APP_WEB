package com.suntek.ibms.api.area;

import com.suntek.ibms.componet.controller.body.Request;
import com.suntek.ibms.componet.controller.body.Response;
import com.suntek.ibms.componet.controller.body.ResponseBody;
import com.suntek.ibms.componet.base.ServiceHandler;
import com.suntek.ibms.manager.AreaManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取组织结构根目录
 */
@Component
public class AreaRootService extends ServiceHandler
{
    @Autowired
    AreaManager areaManager;

    @Override
    public String supportServiceName()
    {
        return "area.root";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        return new ResponseBody()
                .putData("area",areaManager.getRootArea())
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
