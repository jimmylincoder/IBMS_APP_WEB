package com.suntek.ibms.componet;

import com.suntek.ibms.componet.annotation.CheckType;
import com.suntek.ibms.componet.annotation.ParamField;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 服务处理
 *
 * @author jimmy
 */
public abstract class ServiceHandler
{
    //返回响应体拼接类
    protected ResponseBody responseBody = new ResponseBody();

    /**
     * 返回服务名
     */
    public abstract String supportServiceName();

    /**
     * 处理请求
     *
     * @param request 请求参数
     * @return 返回实体
     * @throws Exception 异常处理
     */
    public abstract Response handle(Request request) throws Exception;

    /**
     * @param params
     */
    public void handleParams(Map<String, Object> params) throws Exception
    {

        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields)
        {
            boolean fieldHasAnno = field.isAnnotationPresent(ParamField.class);
            if (fieldHasAnno)
            {
                field.setAccessible(true);
                ParamField paramField = field.getAnnotation(ParamField.class);
                String name = paramField.name();
                String value = (String) params.get(name);
                field.set(this, value);

                CheckType[] checkTypes = paramField.checkType();
                String[] messages = paramField.message();
                if(checkTypes.length > 0)
                {
                    for (CheckType checkType : checkTypes)
                    {
                        if(CheckType.NOT_NULL_AND_BLANK.equals(checkType))
                        {
                            if(value == null || "".equals(value))
                            {
                                throw new Exception(messages[0]);
                            }
                        }
                    }
                }
            }
        }

    }
}
