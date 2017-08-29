package com.suntek.ibms.componet;

import com.alibaba.fastjson.JSON;
import com.suntek.ibms.util.HttpUtil;
import com.suntek.ibms.util.JsonFormatTool;
import com.suntek.ibms.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Http请求入口
 *
 * @author jimmy
 */
@Controller
public class Entrance
{
    @Autowired
    private List<ServiceHandler> handlers;

    //已服务名为key保存接口
    private Map<String, ServiceHandler> mapping;

    /**
     * 把服务保存到map中,以服务名为key
     */
    @PostConstruct
    public void init()
    {
        mapping = new HashMap<String, ServiceHandler>();
        for (ServiceHandler handler : handlers)
        {
            mapping.put(handler.supportServiceName(), handler);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/api",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json;charset=UTF-8")
    public Response handle(HttpServletRequest httpServletRequest)
    {
        Response response = new Response();
        try
        {
            //获取数据字符数组
            byte[] content = HttpUtil.getRequestPostBytes(httpServletRequest);
            String result = new String(content, "UTF-8");

            //获取请求实体
            Request request = JSON.parseObject(result, Request.class);
            LoggerUtil.info(String.format("request -> %s\n%s", request.getServiceName(),
                    JsonFormatTool.formatJson(result)));

            //根据服务名获取对应的服务实现
            ServiceHandler handler = mapping.get(request.getServiceName());

            //找不到服务
            if (handler == null)
            {
                response.setStatus(Response.STATUS_FAILURE);
                response.setErrorMessage("找不到该接口");
            } else
            {
                handler.handleParams(request.getParams());
                //根据服务进行相应的操作
                response = handler.handle(request);
                LoggerUtil.info(String.format("response -> %s\n%s", request.getServiceName(),
                        JsonFormatTool.formatJson(JSON.toJSONString(response))));
            }
        } catch (Exception e)
        {
            LoggerUtil.error("异常:" + e.getMessage());
            response.setErrorMessage(e.getMessage());
            response.setStatus(Response.STATUS_FAILURE);
        }

        return response;
    }
}
