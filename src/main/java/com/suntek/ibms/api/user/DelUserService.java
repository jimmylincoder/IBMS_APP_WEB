package com.suntek.ibms.api.user;

import com.suntek.ibms.componet.annotation.CheckType;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.componet.base.ServiceHandler;
import com.suntek.ibms.componet.controller.body.Request;
import com.suntek.ibms.componet.controller.body.Response;
import com.suntek.ibms.componet.controller.body.ResponseBody;
import com.suntek.ibms.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 删除用户
 *
 * @author jimmy
 */
@Component
public class DelUserService extends ServiceHandler
{
    @Autowired
    private UserManager userManager;

    @ParamField(name = "user_code",checkType = CheckType.NOT_NULL_AND_BLANK,message = "用户账号不能为空")
    ThreadLocal<String> userCode = new ThreadLocal<>();

    @Override
    public String supportServiceName()
    {
        return "user.del";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        userManager.del(userCode.get());
        return new ResponseBody()
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
