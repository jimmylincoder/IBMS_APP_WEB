package com.suntek.ibms.componet;

import com.alibaba.fastjson.JSON;
import com.suntek.ibms.util.HttpUtil;
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
    @RequestMapping(value = "/api/{model}/{action}",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json;charset=UTF-8")
    public Response handle(HttpServletRequest httpServletRequest,
                           @PathVariable String model,
                           @PathVariable String action)
    {
        Response response = new Response();
        try
        {
            //获取数据字符数组
            byte[] content = HttpUtil.getRequestPostBytes(httpServletRequest);
            String result = new String(content,"UTF-8");

            //获取请求实体
            Request request = JSON.parseObject(result, Request.class);

            //根据服务名获取对应的服务实现
            ServiceHandler handler = mapping.get(model + "." + action);

            //找不到服务
            if (handler == null)
            {
                response.setStatus(Response.STATUS_FAILURE);
                response.setErrorMessage("找不到该接口");
            }
            else
            {
                //根据服务进行相应的操作
                response = handler.handle(request);
            }
        }catch (Exception e)
        {
            response.setErrorMessage(e.getMessage());
            response.setStatus(Response.STATUS_FAILURE);
        }

        return  response;
    }
}
