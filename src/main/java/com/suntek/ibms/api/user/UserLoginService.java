package com.suntek.ibms.api.user;

import com.suntek.ibms.componet.Request;
import com.suntek.ibms.componet.Response;
import com.suntek.ibms.componet.ResponseBody;
import com.suntek.ibms.componet.ServiceHandler;
import com.suntek.ibms.componet.annotation.CheckType;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.manager.UserManager;
import com.suntek.ibms.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 用户登录
 *
 * @author jimmy
 */
@Component
public class UserLoginService extends ServiceHandler
{
    //用户名
    @ParamField(name = "user_name", checkType = {CheckType.NOT_NULL_AND_BLANK}, message = "用户名不能为空")
    ThreadLocal<String> userName;

    //密码
    @ParamField(name = "password", checkType = {CheckType.NOT_NULL_AND_BLANK}, message = "密码不能为空")
    ThreadLocal<String> password;

    @Autowired
    UserManager userManager;

    @Override
    public String supportServiceName()
    {
        return "user.login";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        //返回响应体拼接类
        UserVo userVo = userManager.login(userName.get(), password.get());
        if (userVo == null)
        {
            return new ResponseBody()
                    .setStatus(Response.STATUS_FAILURE)
                    .setErrorMessage("用户不存在或密码错误")
                    .bulid();
        }

        return new ResponseBody()
                .putData("user", userVo)
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
