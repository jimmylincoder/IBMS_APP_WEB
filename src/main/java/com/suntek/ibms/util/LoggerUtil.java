package com.suntek.ibms.util;


import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 日志打印
 *
 * @author jimmy
 */
public class LoggerUtil
{
    private static Logger logger = Logger.getLogger(LoggerUtil.class);

    /**
     * 打印错误
     *
     * @param message 错误信息
     */
    public static void error(String message)
    {
        logger.error(message);
    }

    /**
     * 打印信息
     *
     * @param message 信息
     */
    public static void info(String message)
    {
        logger.info(message);
    }

    /**
     * 打印调试信息
     *
     * @param message 调试信息
     */
    public static void debug(String message)
    {
        logger.debug(message);
    }

    /**
     * 打印异常栈
     *
     * @param ex
     * @return
     */
    public static String getExceptionMessage(Throwable ex)
    {
        StringBuffer sb = new StringBuffer();
        //exception信息
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null)
        {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        return sb.toString();
    }
}
