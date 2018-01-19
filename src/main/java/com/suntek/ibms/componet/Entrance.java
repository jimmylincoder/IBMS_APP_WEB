package com.suntek.ibms.componet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.suntek.ibms.util.HttpUtil;
import com.suntek.ibms.util.JsonFormatTool;
import com.suntek.ibms.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
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

    //加密key
    @Value("${aes.key}")
    String ASE_KEY;

    //是否开启加密
    @Value("${encrypotor.is}")
    boolean IS_ENCRYPOTOR;

    //加密
    @Autowired
    Encryptor encryptor;

    @Value("${log.is_log}")
    private boolean isLog;

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
    public byte[] handle(HttpServletRequest httpServletRequest) throws UnsupportedEncodingException
    {
        Response response = new Response();

        //返回字符串
        byte[] data = null;
        String responseStr;
        try
        {
            //获取数据字符数组
            byte[] content = HttpUtil.getRequestPostBytes(httpServletRequest);
            String result;
            //判断是否开启加密
            if (IS_ENCRYPOTOR)
            {
                //解密数据
                result = new String(encryptor.decode(content, ASE_KEY), "UTF-8");
            }
            else
            {
                //不解密数据
                result = new String(content, "UTF-8");
            }
            //获取请求实体
            Request request = JSON.parseObject(result, Request.class);
            //是否打印日志
            if (isLog)
                LoggerUtil.info(String.format("request -> %s  udid:%s\n%s", request.getServiceName(),
                        request.getUdid(), JsonFormatTool.formatJson(result)));

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
                if (isLog)
                    LoggerUtil.info(String.format("response -> %s  udid:%s\n%s", request.getServiceName(),
                            request.getUdid(), JsonFormatTool.formatJson(JSON.toJSONString(response))));
            }

            //返回数据
            if (IS_ENCRYPOTOR)
            {
                //加密数据
                data = encryptor.encode(JSON.toJSONString(
                        response, SerializerFeature.WriteMapNullValue).getBytes(),
                        ASE_KEY
                );
            }
            else
            {
                data = JSON.toJSONString(response, SerializerFeature.WriteMapNullValue).getBytes();
            }
        } catch (Exception e)
        {
            LoggerUtil.error("异常:" + getExceptionMessage(e));
            response.setErrorMessage(getExceptionMessage(e));
            response.setStatus(Response.STATUS_FAILURE);
            data = JSON.toJSONString(response, SerializerFeature.WriteMapNullValue).getBytes();

            if (IS_ENCRYPOTOR)
            {
                data = new String(encryptor.encode(data, ASE_KEY)).getBytes();
            }
        }

        return data;
    }


    private String getExceptionMessage(Throwable ex)
    {
        StringBuffer sb = new StringBuffer();
        //exception信息
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null)
        {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        return sb.toString();
    }
}
