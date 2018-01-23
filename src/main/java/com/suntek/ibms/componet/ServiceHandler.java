package com.suntek.ibms.componet;

import com.suntek.ibms.componet.annotation.CheckType;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.exception.ValidateException;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 服务处理
 *
 * @author jimmy
 */
public abstract class ServiceHandler
{
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
            validateParams(params, field);
        }
    }

    /**
     * 校验各个参数
     *
     * @param params
     * @param field
     * @throws Exception
     */
    private void validateParams(Map<String, Object> params, Field field) throws ValidateException
    {
        boolean fieldHasAnno = field.isAnnotationPresent(ParamField.class);
        if (fieldHasAnno)
        {
            field.setAccessible(true);
            ParamField paramField = field.getAnnotation(ParamField.class);
            String name = paramField.name();
            String value = (String) params.get(name);
            ThreadLocal<String> valueLocal = new ThreadLocal<>();
            valueLocal.set(value);
            try
            {
                field.set(this, valueLocal);
            } catch (IllegalAccessException e)
            {
                throw new ValidateException(e.getMessage());
            }
            CheckType[] checkTypes = paramField.checkType();
            String[] messages = paramField.message();
            if (checkTypes.length < 0)
                return;
            for (CheckType checkType : checkTypes)
            {
                handleByType(checkType, value, messages);
            }
        }
    }

    /**
     * 根据不同类型判断并返回校验信息
     *
     * @param checkType
     * @param value
     * @param messages
     * @throws Exception
     */
    private void handleByType(CheckType checkType, String value, String[] messages) throws ValidateException
    {
        if (CheckType.NOT_NULL_AND_BLANK.equals(checkType))
        {
            if (value == null || "".equals(value))
            {
                throw new ValidateException(messages[0]);
            }
        }
    }
}
