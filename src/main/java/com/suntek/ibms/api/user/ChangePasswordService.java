package com.suntek.ibms.api.user;

import com.suntek.ibms.componet.Request;
import com.suntek.ibms.componet.Response;
import com.suntek.ibms.componet.ResponseBody;
import com.suntek.ibms.componet.ServiceHandler;
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
    String userCode;

    @ParamField(name = "new_password", checkType = CheckType.NOT_NULL_AND_BLANK, message = "新密码不能为空")
    String newPassword;

    @ParamField(name = "old_password", checkType = CheckType.NOT_NULL_AND_BLANK, message = "旧密码不能为空")
    String oldPassword;

    @Override
    public String supportServiceName()
    {
        return "user.change_password";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        userManager.changePassword(userCode, newPassword, oldPassword);
        return new ResponseBody()
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}