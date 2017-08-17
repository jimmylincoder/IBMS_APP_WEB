package com.suntek.ibms.componet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 视频后台服务engine
 *
 * @author jimmy
 */
@Component
public class MediaHttpEngine
{
    @Autowired
    HttpClient httpClient;

    @Value("${camera.server}")
    String cameraServerIp;

    /**
     * 视频后台请求
     *
     * @param action
     * @param params
     * @return
     */
    public MediaResponse request(String action, Map<String, Object> params) throws Exception
    {
        MediaResponse mediaResponse = new MediaResponse();
        Map<String, Object> response = httpClient.postByXml(params, action);
        String statusCode = (String) response.get("StatusCode");
        String description = (String) response.get("Description");
        String session = (String) response.get("session");

        if(!"0".equals(statusCode))
        {
            throw new Exception("视频服务返回错误码:" + statusCode
                    + "\n错误描述:" + description
                    + "\nsession:" + session);
        }

        if (statusCode != null && !"".equals(statusCode))
        {
            mediaResponse.setStatusCode(Integer.parseInt(statusCode));
            response.remove("StatusCode");
        }

        if (description != null && !"".equals(description))
        {
            mediaResponse.setDescription(description);
            response.remove("Description");
        }

        if (session != null && !"".equals(session))
        {
            mediaResponse.setSession(session);
            response.remove("session");
        }
        mediaResponse.setContent(response);

        return mediaResponse;
    }

}
