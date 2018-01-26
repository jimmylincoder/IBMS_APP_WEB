package com.suntek.ibms.api.log;

import com.suntek.ibms.componet.controller.body.Request;
import com.suntek.ibms.componet.controller.body.Response;
import com.suntek.ibms.componet.controller.body.ResponseBody;
import com.suntek.ibms.componet.base.ServiceHandler;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.util.LoggerUtil;
import org.springframework.stereotype.Component;

/**
 * app异常记录
 *
 * @author jimmy
 */
@Component
public class CrashService extends ServiceHandler
{
    @ParamField(name = "error_message")
    ThreadLocal<String> errorMessage = new ThreadLocal<>();

    @Override
    public String supportServiceName()
    {
        return "crash.log";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        LoggerUtil.error(errorMessage.get());
        return new ResponseBody()
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
