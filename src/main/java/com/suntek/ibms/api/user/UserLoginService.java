package com.suntek.ibms.api.user;

import com.suntek.ibms.componet.Request;
import com.suntek.ibms.componet.Response;
import com.suntek.ibms.componet.ServiceHandler;
import com.suntek.ibms.manager.UserManager;
import com.suntek.ibms.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录
 *
 * @author jimmy
 */
@Component
public class UserLoginService implements ServiceHandler
{
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
        Response response = new Response();
        Map<String,Object> params = request.getParams();
        String userName = (String) params.get("user_name");
        String password = (String) params.get("password");

        if(userName == null || "".equals(userName))
        {
            response.setStatus(Response.STATUS_FAILURE);
            response.setErrorMessage("用户名不能为空");
            return response;
        }

        if(password == null || "".equals(password))
        {
            response.setStatus(Response.STATUS_FAILURE);
            response.setErrorMessage("密码不能为空");
            return response;
        }

        UserVo userVo = userManager.login(userName,password);
        if(userVo == null)
        {
            response.setStatus(Response.STATUS_FAILURE);
            response.setErrorMessage("用户不存在或密码错误");
            return response;
        }

        Map<String,Object> result = new HashMap<>();
        response.setStatus(Response.STATUS_SUCCESS);
        result.put("user", userVo);
        response.setContent(result);
        return response;
    }
}
