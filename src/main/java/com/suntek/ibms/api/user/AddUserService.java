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
 * 添加用户
 *
 * @author jimmy
 */
@Component
public class AddUserService extends ServiceHandler
{
    @Autowired
    private UserManager userManager;

    @ParamField(name = "user_name",checkType = CheckType.NOT_NULL_AND_BLANK,message = "用户名不能为空")
    ThreadLocal<String> userName = new ThreadLocal<>();

    @ParamField(name = "password",checkType = CheckType.NOT_NULL_AND_BLANK,message = "密码不能为空")
    ThreadLocal<String> password = new ThreadLocal<>();

    @ParamField(name = "name",checkType = CheckType.NOT_NULL_AND_BLANK,message = "名称不能为空")
    ThreadLocal<String> name = new ThreadLocal<>();

    @Override
    public String supportServiceName()
    {
        return "user.add";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        userManager.addUser(userName.get(),password.get(),name.get());
        return new ResponseBody()
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
