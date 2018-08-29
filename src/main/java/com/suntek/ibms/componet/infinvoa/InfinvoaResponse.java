package com.suntek.ibms.componet.infinvoa;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 英飞拓平台返回结构
 *
 * @author jimmy
 */
public class InfinvoaResponse
{
    @JSONField(name = "code")
    private String code;

    @JSONField(name = "desc")
    private String desc;

    private String cookie;

    @JSONField(name = "msg")
    private Object msg;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public Object getMsg()
    {
        return msg;
    }

    public void setMsg(Object msg)
    {
        this.msg = msg;
    }

    public String getCookie()
    {
        return cookie;
    }

    public void setCookie(String cookie)
    {
        this.cookie = cookie;
    }
}
