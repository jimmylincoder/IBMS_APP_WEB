package com.suntek.ibms.exception;

/**
 * 校验异常类
 *
 * @author jimmy
 */
public class ValidateException extends Exception
{
    public ValidateException()
    {
        super();
    }

    public ValidateException(String message)
    {
        super(message);
    }

    public ValidateException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ValidateException(Throwable cause)
    {
        super(cause);
    }

    protected ValidateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
