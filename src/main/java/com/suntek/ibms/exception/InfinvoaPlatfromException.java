package com.suntek.ibms.exception;

/**
 * 英飞拓异常
 *
 * @author jimmy
 */
public class InfinvoaPlatfromException extends Exception
{
    public InfinvoaPlatfromException()
    {
        super();
    }

    public InfinvoaPlatfromException(String message)
    {
        super(message);
    }

    public InfinvoaPlatfromException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public InfinvoaPlatfromException(Throwable cause)
    {
        super(cause);
    }

    protected InfinvoaPlatfromException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
