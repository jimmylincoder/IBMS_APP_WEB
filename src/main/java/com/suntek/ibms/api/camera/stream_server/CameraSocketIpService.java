package com.suntek.ibms.api.camera.stream_server;

import com.suntek.ibms.componet.controller.body.Request;
import com.suntek.ibms.componet.controller.body.Response;
import com.suntek.ibms.componet.controller.body.ResponseBody;
import com.suntek.ibms.componet.base.ServiceHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 获取tcp连接地址
 *
 * @author jimmy
 */
@Component
public class CameraSocketIpService extends ServiceHandler
{
    @Value("${camera.socket_ip}")
    String ip;

    @Value("${camera.socket_port}")
    String port;

    @Override
    public String supportServiceName()
    {
        return "camera.socket_address";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        return new ResponseBody()
                .setStatus(Response.STATUS_SUCCESS)
                .putData("ip", ip)
                .putData("port", port)
                .bulid();
    }
}
