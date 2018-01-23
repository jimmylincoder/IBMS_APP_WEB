package com.suntek.ibms.exception;

/**
 * 视频播放异常类
 *
 * @author jimmy
 */
public class MediaException extends Exception
{
    public MediaException()
    {
        super();
    }

    public MediaException(String message)
    {
        super(message);
    }

    public MediaException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public MediaException(Throwable cause)
    {
        super(cause);
    }

    protected MediaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
