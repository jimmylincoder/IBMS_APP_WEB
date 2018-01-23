package com.suntek.ibms.componet.controller;

import com.alibaba.fastjson.JSON;
import com.suntek.ibms.componet.base.ServiceHandler;
import com.suntek.ibms.componet.controller.body.Request;
import com.suntek.ibms.componet.controller.body.Response;
import com.suntek.ibms.componet.controller.encryption.Encryptor;
import com.suntek.ibms.exception.CameraException;
import com.suntek.ibms.exception.MediaException;
import com.suntek.ibms.exception.UserException;
import com.suntek.ibms.exception.ValidateException;
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
        Request request = null;
        //返回字符串
        byte[] data = null;
        try
        {
            //获取数据字符数组
            byte[] content = HttpUtil.getRequestPostBytes(httpServletRequest);
            String result;
            //判断是否开启加密
            if (IS_ENCRYPOTOR)
                //解密数据
                result = new String(encryptor.decode(content, ASE_KEY), "UTF-8");
            else
                //不解密数据
                result = new String(content, "UTF-8");

            //获取请求实体
            request = JSON.parseObject(result, Request.class);
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
            }
        } catch (Exception e)
        {
            response = makeResponseByException(response, e);
        }
        if (isLog)
            LoggerUtil.info(String.format("response -> %s  udid:%s\n%s", request.getServiceName(),
                    request.getUdid(), JsonFormatTool.formatJson(JSON.toJSONString(response))));
        //返回数据
        if (IS_ENCRYPOTOR)
            //加密数据
            data = encryptor.encode(JSON.toJSONString(response).getBytes(), ASE_KEY);
        else
            data = JSON.toJSONString(response).getBytes();

        return data;
    }

    /**
     * 根据不同异常返回异常信息
     *
     * @param response
     * @param e
     * @return
     */
    private Response makeResponseByException(Response response, Exception e)
    {
        if (e instanceof ValidateException || e instanceof UserException ||
                e instanceof MediaException || e instanceof CameraException)
        {
            response.setErrorMessage(e.getMessage());
            response.setStatus(Response.STATUS_FAILURE);
        } else
        {
            LoggerUtil.error("异常:" + getExceptionMessage(e));
            response.setErrorMessage(getExceptionMessage(e));
            response.setStatus(Response.STATUS_FAILURE);
        }
        return response;
    }


    /**
     * 打印异常栈
     *
     * @param ex
     * @return
     */
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
