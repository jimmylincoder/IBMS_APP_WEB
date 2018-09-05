package com.suntek.ibms.api.infinvoa_platform;

import com.suntek.ibms.componet.annotation.CheckType;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.componet.base.ServiceHandler;
import com.suntek.ibms.componet.controller.body.Request;
import com.suntek.ibms.componet.controller.body.Response;
import com.suntek.ibms.componet.controller.body.ResponseBody;
import com.suntek.ibms.manager.InfinvoaPlatfromManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取平台信息
 *
 *
 * @author jimmy
 */
@Component
public class FindInfinvoaPlatfromInfoService extends ServiceHandler
{
    @Autowired
    private InfinvoaPlatfromManager infinvoaPlatfromManager;

    @ParamField(name = "id",checkType = CheckType.NOT_NULL_AND_BLANK,message = "id不能为空")
    ThreadLocal<String> id = new ThreadLocal<>();

    @Override
    public String supportServiceName()
    {
        return "infinvoa_platform.info";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        return new ResponseBody()
                .setStatus(Response.STATUS_SUCCESS)
                .putData("infinvoa_platform",infinvoaPlatfromManager.findInfo(id.get()))
                .bulid();
    }
}
