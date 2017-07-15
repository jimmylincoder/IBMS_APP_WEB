package com.suntek.ibms.componet.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解，用于自动将请求参数注入类属性
 *
 * @author jimmy
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD})
public @interface ParamField
{
    //参数名称
    String name() default "";

    //校验类型
    CheckType[] checkType() default {};

    //校验错误返回信息
    String[] message() default {};
}
