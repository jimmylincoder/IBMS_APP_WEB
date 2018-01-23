package com.suntek.ibms.componet.controller.body;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回实体builder
 *
 * @author jimmy
 */
public class ResponseBody
{
    private Response response = new Response();

    private Map<String,Object> content = new HashMap<>();

    public ResponseBody()
    {
        content.clear();
    }

    public ResponseBody putData(String key,Object value)
    {
        content.put(key,value);
        return this;
    }

    public ResponseBody putAll(Map<String,Object> values)
    {
        response.setContent(values);
        return this;
    }

    public ResponseBody setStatus(int status)
    {
        response.setStatus(status);
        return this;
    }

    public ResponseBody setErrorMessage(String message)
    {
        response.setErrorMessage(message);
        return this;
    }

    public Response bulid()
    {
        response.setContent(content);
        return response;
    }
}
