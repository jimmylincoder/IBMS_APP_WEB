package com.suntek.ibms.componet;

import com.squareup.okhttp.*;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.suntek.ibms.util.LoggerUtil;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * httpclient
 *
 * @author jimmy
 */
@Component
@Scope(value = "prototype")
public class HttpClient
{
    //数据编码格式
    public static final MediaType XMLMTYPE = MediaType.parse("application/xml; charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient();

    static
    {
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(10, TimeUnit.SECONDS);
        client.setWriteTimeout(10, TimeUnit.SECONDS);
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
        LoggerUtil.info(String.format("xml request->%s\n%s",url,formatXML(request)));
        com.squareup.okhttp.RequestBody body = RequestBody.create(XMLMTYPE, request);
        Request request1 = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response1 = client.newCall(request1).execute();
        String resStr = response1.body().string();
        LoggerUtil.info(String.format("xml response->%s\n%s",url,formatXML(resStr)));
        response = xmlToMap(resStr);

        return response;
    }

    /**
     * xml转map
     *
     * @param xml
     * @return
     */
    private Map<String, Object> xmlToMap(String xml)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        try
        {
            Document document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();
            map = xml2mapWithAttr(root);
        } catch (DocumentException e)
        {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * xml转map 带属性
     *
     * @param
     * @return
     */
    private Map xml2mapWithAttr(Element element)
    {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Element> list = element.elements();
        List<Attribute> listAttr0 = element.attributes(); // 当前节点的所有属性的list
        for (Attribute attr : listAttr0)
        {
            map.put("@" + attr.getName(), attr.getValue());
        }
        if (list.size() > 0)
        {

            for (int i = 0; i < list.size(); i++)
            {
                Element iter = list.get(i);
                List mapList = new ArrayList();

                if (iter.elements().size() > 0)
                {
                    Map m = xml2mapWithAttr(iter);
                    if (map.get(iter.getName()) != null)
                    {
                        Object obj = map.get(iter.getName());
                        if (!(obj instanceof List))
                        {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(m);
                        }
                        if (obj instanceof List)
                        {
                            mapList = (List) obj;
                            mapList.add(m);
                        }
                        map.put(iter.getName(), mapList);
                    } else
                        map.put(iter.getName(), m);
                } else
                {

                    List<Attribute> listAttr = iter.attributes(); // 当前节点的所有属性的list
                    Map<String, Object> attrMap = null;
                    boolean hasAttributes = false;
                    if (listAttr.size() > 0)
                    {
                        hasAttributes = true;
                        attrMap = new LinkedHashMap<String, Object>();
                        for (Attribute attr : listAttr)
                        {
                            attrMap.put("@" + attr.getName(), attr.getValue());
                        }
                    }

                    if (map.get(iter.getName()) != null)
                    {
                        Object obj = map.get(iter.getName());
                        if (!(obj instanceof List))
                        {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            // mapList.add(iter.getText());
                            if (hasAttributes)
                            {
                                attrMap.put("#text", iter.getText());
                                mapList.add(attrMap);
                            } else
                            {
                                mapList.add(iter.getText());
                            }
                        }
                        if (obj instanceof List)
                        {
                            mapList = (List) obj;
                            // mapList.add(iter.getText());
                            if (hasAttributes)
                            {
                                attrMap.put("#text", iter.getText());
                                mapList.add(attrMap);
                            } else
                            {
                                mapList.add(iter.getText());
                            }
                        }
                        map.put(iter.getName(), mapList);
                    } else
                    {
                        // map.put(iter.getName(), iter.getText());
                        if (hasAttributes)
                        {
                            attrMap.put("#text", iter.getText());
                            map.put(iter.getName(), attrMap);
                        } else
                        {
                            map.put(iter.getName(), iter.getText());
                        }
                    }
                }
            }
        } else
        {
            // 根节点的
            if (listAttr0.size() > 0)
            {
                map.put("#text", element.getText());
            } else
            {
                map.put(element.getName(), element.getText());
            }
        }
        return map;
    }

    private String mapToXML(Map map)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"GB2312\"?><Request>");
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

    public String formatXML(String inputXML) throws Exception
    {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new StringReader(inputXML));
        String requestXML = null;
        XMLWriter writer = null;
        if (document != null)
        {
            try
            {
                StringWriter stringWriter = new StringWriter();
                OutputFormat format = new OutputFormat(" ", true);
                writer = new XMLWriter(stringWriter, format);
                writer.write(document);
                writer.flush();
                requestXML = stringWriter.getBuffer().toString();
            } finally
            {
                if (writer != null)
                {
                    try
                    {
                        writer.close();
                    } catch (IOException e)
                    {
                    }
                }
            }
        }
        return requestXML;
    }
}
