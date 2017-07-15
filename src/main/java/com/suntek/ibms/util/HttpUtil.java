package com.suntek.ibms.util;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Http工具类
 *
 * @author jimmy
 * */
public class HttpUtil
{
    /**
     * 请求参数转成字节数组
     *
     * @param request 请求
     * @return 字节数组
     * @throws IOException
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException
    {
        int contentLength = request.getContentLength();
        /*当无请求参数时，request.getContentLength()返回-1 */
        if (contentLength < 0)
        {
            return new byte[]{};
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; )
        {

            int readlen = request.getInputStream().read(buffer, i, contentLength - i);
            if (readlen == -1)
            {
                break;
            }
            i += readlen;
        }
        return buffer;
    }
}
