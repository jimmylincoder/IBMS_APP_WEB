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
 * 更新英飞拓平台服务信息
 *
 * @author jimmy
 */
@Component
public class UpdateInfinvoaPlatfromService extends ServiceHandler
{
    @Autowired
    private InfinvoaPlatfromManager infinvoaPlatfromManager;

    @ParamField(name = "id",checkType = CheckType.NOT_NULL_AND_BLANK,message = "id不能为空")
    ThreadLocal<String> id = new ThreadLocal<>();

    @ParamField(name = "name")
    ThreadLocal<String> name = new ThreadLocal<>();

    @ParamField(name = "ip")
    ThreadLocal<String> ip = new ThreadLocal<>();

    @ParamField(name = "port")
    ThreadLocal<String> port = new ThreadLocal<>();

    @ParamField(name = "user_name")
    ThreadLocal<String> userName = new ThreadLocal<>();

    @ParamField(name = "password")
    ThreadLocal<String> password = new ThreadLocal<>();

    @Override
    public String supportServiceName()
    {
        return "infinvoa_platfrom.update";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        infinvoaPlatfromManager.update(id.get(),name.get(),ip.get(),port.get(),userName.get(),password.get());
        return new ResponseBody()
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
