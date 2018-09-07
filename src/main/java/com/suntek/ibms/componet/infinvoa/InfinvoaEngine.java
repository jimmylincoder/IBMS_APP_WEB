package com.suntek.ibms.componet.infinvoa;

import com.alibaba.fastjson.JSON;
import com.squareup.okhttp.Response;
import com.suntek.ibms.componet.http.HttpClient;
import com.suntek.ibms.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 英飞拓请求
 *
 * @author jimmy
 */
@Component
public class InfinvoaEngine
{
    @Autowired
    private HttpClient httpClient;

    public InfinvoaResponse requestByGet(String ip, String action, Map<String, String> header,
                                         Map<String, Object> params) throws IOException, InfinvoaException
    {
        String url = getUrl(ip, action, params);
        LoggerUtil.info(String.format("英飞拓平台请求%s 请求头[%s]", url, header.toString()));
        Response response = httpClient.get(url, header);
        String responseStr = response.body().string();
        LoggerUtil.info(String.format("返回报文%s", responseStr));
        InfinvoaResponse infinvoaResponse = JSON.parseObject(responseStr, InfinvoaResponse.class);
        if (!"0".equals(infinvoaResponse.getCode()))
        {
            throw new InfinvoaException(String.format("英飞拓平台返回错误码[%s] 错误信息:[%s]",
                    infinvoaResponse.getCode(), infinvoaResponse.getDesc()));
        }
        String cookie = response.header("Set-Cookie");
        infinvoaResponse.setCookie(cookie);
        return infinvoaResponse;
    }

    public InfinvoaResponse requestByPostForm(String ip, String action, Map<String, Object> header,
                                              Map<String, Object> params) throws IOException, InfinvoaException
    {
        String url = getUrl(ip, action, new HashMap<>());
        LoggerUtil.info(String.format("英飞拓平台请求%s 请求头[%s]", url, header.toString()));
        Response response = httpClient.postByForm(url, header, params);
        String responseStr = response.body().string();
        LoggerUtil.info(String.format("返回报文%s", responseStr));
        InfinvoaResponse infinvoaResponse = JSON.parseObject(responseStr, InfinvoaResponse.class);
        if (!"0".equals(infinvoaResponse.getCode()))
        {
            throw new InfinvoaException(String.format("英飞拓平台返回错误码[%s] 错误信息:[%s]",
                    infinvoaResponse.getCode(), infinvoaResponse.getDesc()));
        }
        return infinvoaResponse;
    }

    private String getUrl(String ip, String action, Map<String, Object> params)
    {
        String urlParams = "";
        if (!params.isEmpty())
        {
            for (String key : params.keySet())
            {
                String value = (String) params.get(key);
                urlParams += key + "=" + value + "&";
            }
        }
        return "http://" + ip + action + "?" + urlParams;
    }
}

