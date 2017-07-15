package com.suntek.ibms.componet.annotation;

/**
 * 校验类型枚举
 *
 * @author jimmy
 */
public enum CheckType
{
    //值不为空和null
    NOT_NULL_AND_BLANK,

    //值不为null
    NOT_NULL,

    //值不为空
    NOT_BLANK,

    //邮箱校验
    MAIL,

    //手机号码校验
    PHONE
}
