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
 * 添加英飞拓平台服务
 *
 * @author jimmy
 */
@Component
public class AddInfovoaPlatformService extends ServiceHandler
{
    @Autowired
    private InfinvoaPlatfromManager infinvoaPlatfromManager;

    @ParamField(name = "name",checkType = CheckType.NOT_NULL_AND_BLANK,message = "名称不能为空")
    ThreadLocal<String> name = new ThreadLocal<>();

    @ParamField(name = "ip",checkType = CheckType.NOT_NULL_AND_BLANK,message = "ip不能为空")
    ThreadLocal<String> ip = new ThreadLocal<>();

    @ParamField(name = "port",checkType = CheckType.NOT_NULL_AND_BLANK,message = "端口不能为空")
    ThreadLocal<String> port = new ThreadLocal<>();

    @ParamField(name = "user_name",checkType = CheckType.NOT_NULL_AND_BLANK,message = "用户名不能为空")
    ThreadLocal<String> userName = new ThreadLocal<>();

    @ParamField(name = "password",checkType = CheckType.NOT_NULL_AND_BLANK,message = "密码不能为空")
    ThreadLocal<String> password = new ThreadLocal<>();

    @Override
    public String supportServiceName()
    {
        return "infinvoa_platform.add";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        infinvoaPlatfromManager.add(name.get(),ip.get(),port.get(),userName.get(),password.get());
        return new ResponseBody()
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
