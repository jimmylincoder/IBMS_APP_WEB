package com.suntek.ibms.componet.controller.body;


import java.util.HashMap;
import java.util.Map;

/**
 * builder模式 拼装请求实体
 *
 * @author jimmy
 */
public class RequestBody
{
    private Request request = new Request();

    private Map<String,Object> params = new HashMap<>();

    public RequestBody setSerivce(String service)
    {
        request.setServiceName(service);
        return this;
    }

    public RequestBody setUdid(String udid)
    {
        request.setUdid(udid);
        return this;
    }

    public RequestBody putParams(String key,String value)
    {
        params.put(key,value);
        return this;
    }

    public Request build()
    {
        request.setParams(params);
        return request;
    }
}
