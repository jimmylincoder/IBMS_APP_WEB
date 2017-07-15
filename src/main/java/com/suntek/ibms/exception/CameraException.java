package com.suntek.ibms.exception;

/**
 * 摄像机异常类
 *
 * @author jimmy
 */
public class CameraException extends Exception
{
    public CameraException()
    {
        super();
    }

    public CameraException(String message)
    {
        super(message);
    }

    public CameraException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public CameraException(Throwable cause)
    {
        super(cause);
    }

    protected CameraException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
