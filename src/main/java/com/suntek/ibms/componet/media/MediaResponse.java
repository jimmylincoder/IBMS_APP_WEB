package com.suntek.ibms.componet.media;

import java.util.Map;

/**
 * 视频后台请求响应体
 *
 * @author jimmy
 */
public class MediaResponse
{
    public final static int SUCCESS = 0;

    private int statusCode;

    private String description;

    private String session;

    private String requestId;

    private Map<String,Object> content;

    public int getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(int statusCode)
    {
        this.statusCode = statusCode;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getSession()
    {
        return session;
    }

    public void setSession(String session)
    {
        this.session = session;
    }

    public Map<String, Object> getContent()
    {
        return content;
    }

    public void setContent(Map<String, Object> content)
    {
        this.content = content;
    }

    public void setRequestId(String requestId)
    {
        this.requestId = requestId;
    }

    public String getRequestId()
    {
        return requestId;
    }
}
