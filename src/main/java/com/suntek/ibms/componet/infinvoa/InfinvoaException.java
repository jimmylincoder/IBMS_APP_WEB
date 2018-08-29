package com.suntek.ibms.componet.infinvoa;

/**
 * 英飞拓异常
 *
 * @author jimmy
 */
public class InfinvoaException extends Exception
{
    public InfinvoaException()
    {
        super();
    }

    public InfinvoaException(String message)
    {
        super(message);
    }

    public InfinvoaException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public InfinvoaException(Throwable cause)
    {
        super(cause);
    }

    protected InfinvoaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
