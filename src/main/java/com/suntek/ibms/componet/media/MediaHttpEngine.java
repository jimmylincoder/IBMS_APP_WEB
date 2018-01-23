package com.suntek.ibms.componet.media;

import com.suntek.ibms.componet.http.HttpClient;
import com.suntek.ibms.exception.MediaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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

    private AtomicInteger requestId = new AtomicInteger(0);

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
        params.put("RequestID",generateRequestId());
        Map<String, Object> response = httpClient.postByXml(params,cameraServerIp +  action);
        String statusCode = (String) response.get("StatusCode");
        String description = (String) response.get("Description");
        String session = (String) response.get("session");
        String requestId = (String) response.get("RequestID");
        if (statusCode != null && !"".equals(statusCode))
        {
            if (!"0".equals(statusCode))
            {
                throw new MediaException("视频服务返回错误码:" + statusCode
                        + "\n错误描述:" + description
                        + "\nsession:" + session);
            }

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

        if(requestId != null && !"".equals(requestId))
        {
            mediaResponse.setRequestId(requestId);
            response.remove("RequestID");
        }
        mediaResponse.setContent(response);

        return mediaResponse;
    }

    /**
     * 自增requestId
     *
     * @return
     */
    private int generateRequestId()
    {
        return requestId.incrementAndGet();
    }

}
