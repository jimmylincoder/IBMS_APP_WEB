package com.suntek.ibms.api.user;

import com.suntek.ibms.componet.Request;
import com.suntek.ibms.componet.Response;
import com.suntek.ibms.componet.ServiceHandler;
import org.springframework.stereotype.Component;

/**
 * 获取用户信息
 *
 * @author jimmy
 */
@Component
public class UserInfoService implements ServiceHandler
{
    @Override
    public String supportServiceName()
    {
        return "user.getUerInfo";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        return null;
    }
}
