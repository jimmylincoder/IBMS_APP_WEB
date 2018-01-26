package com.suntek.ibms.api.user;

import com.suntek.ibms.componet.controller.body.Request;
import com.suntek.ibms.componet.controller.body.Response;
import com.suntek.ibms.componet.controller.body.ResponseBody;
import com.suntek.ibms.componet.base.ServiceHandler;
import com.suntek.ibms.componet.annotation.CheckType;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 修改密码
 *
 * @author jimmy
 */
@Component
public class ChangePasswordService extends ServiceHandler
{
    @Autowired
    UserManager userManager;

    @ParamField(name = "user_code", checkType = CheckType.NOT_NULL_AND_BLANK, message = "用户名不能为空")
    ThreadLocal<String> userCode = new ThreadLocal<>();

    @ParamField(name = "new_password", checkType = CheckType.NOT_NULL_AND_BLANK, message = "新密码不能为空")
    ThreadLocal<String> newPassword = new ThreadLocal<>();

    @ParamField(name = "old_password", checkType = CheckType.NOT_NULL_AND_BLANK, message = "旧密码不能为空")
    ThreadLocal<String> oldPassword = new ThreadLocal<>();

    @Override
    public String supportServiceName()
    {
        return "user.change_password";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        userManager.changePassword(userCode.get(), newPassword.get(), oldPassword.get());
        return new ResponseBody()
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}