package com.suntek.ibms.componet;

import com.squareup.okhttp.*;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * httpclient
 *
 * @author jimmy
 */
@Component
public class HttpClient
{
    //数据编码格式
    public static final MediaType XMLMTYPE = MediaType.parse("application/xml; charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient();

    static
    {
        client.setConnectTimeout(2, TimeUnit.SECONDS);
        client.setReadTimeout(2, TimeUnit.SECONDS);
        client.setWriteTimeout(2, TimeUnit.SECONDS);
    }

    public static void main(String[] args)
    {
        HttpClient httpClient = new HttpClient();
        Map<String,Object> params = new HashMap<>();
        params.put("DeviceID","44030100001310000002");
        params.put("DeviceIP","172.16.16.111");
        params.put("DevicePort","5060");
        params.put("DeviceChn","33");
        params.put("DeviceUser","admin");
        params.put("DevicePass","suntek123");
        params.put("BeginTime","");
        params.put("EndTime","");
        try
        {
            Map<String,Object> map = httpClient.postByXml(params,"http://192.168.1.112:8088/mobileservice/play");
            System.out.print(map);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * xml请求
     *
     * @param params
     * @param url
     * @return
     */
    public Map<String, Object> postByXml(Map<String, Object> params, String url) throws Exception
    {
        Map<String, Object> response = new HashMap<>();
        String request = mapToXML(params);
        com.squareup.okhttp.RequestBody body = RequestBody.create(XMLMTYPE, request);
        Request request1 = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response1 = client.newCall(request1).execute();
        response = xmlToMap(response1.body().string());

        return response;
    }

    /**
     * xml转map
     *
     * @param xml
     * @return
     */
    private  Map<String, Object> xmlToMap(String xml)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        try
        {
            Document document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();
            Iterator<Element> it = root.elementIterator();
            while (it.hasNext())
            {
                Element element = it.next();
                map.put(element.getName(), element.getTextTrim());
            }
        } catch (DocumentException e)
        {
            e.printStackTrace();
        }
        return map;
    }

    private String mapToXML(Map map)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Request>");
        mapToXMLTest2(map, sb);
        sb.append("</Request>");

        return sb.toString();

    }

    private void mapToXMLTest2(Map map, StringBuffer sb)
    {
        Set set = map.keySet();
        for (Iterator it = set.iterator(); it.hasNext(); )
        {
            String key = (String) it.next();
            Object value = map.get(key);
            if (null == value)
                value = "";
            if (value.getClass().getName().equals("java.util.ArrayList"))
            {
                ArrayList list = (ArrayList) map.get(key);
                sb.append("<" + key + ">");
                for (int i = 0; i < list.size(); i++)
                {
                    HashMap hm = (HashMap) list.get(i);
                    mapToXMLTest2(hm, sb);
                }
                sb.append("</" + key + ">");

            } else
            {
                if (value instanceof HashMap)
                {
                    sb.append("<" + key + ">");
                    mapToXMLTest2((HashMap) value, sb);
                    sb.append("</" + key + ">");
                } else
                {
                    sb.append("<" + key + ">" + value + "</" + key + ">");
                }

            }

        }
    }
}
