package com.suntek.ibms.api.infinvoa_platform;

import com.suntek.ibms.componet.base.ServiceHandler;
import com.suntek.ibms.componet.controller.body.Request;
import com.suntek.ibms.componet.controller.body.Response;
import com.suntek.ibms.componet.controller.body.ResponseBody;
import com.suntek.ibms.manager.InfinvoaPlatfromManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 刷新通道信息
 *
 * @author jimmy
 */
@Component
public class RefreshInfoService extends ServiceHandler
{
    @Autowired
    private InfinvoaPlatfromManager infinvoaPlatfromManager;

    @Override
    public String supportServiceName()
    {
        return "infinvoa_platform.refresh";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        infinvoaPlatfromManager.refresh();
        return new ResponseBody()
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
