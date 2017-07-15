package com.suntek.ibms.api.area;

import com.suntek.ibms.componet.Request;
import com.suntek.ibms.componet.Response;
import com.suntek.ibms.componet.ServiceHandler;
import org.springframework.stereotype.Component;

/**
 * 区域列表
 *
 * @author jimmy
 */
@Component
public class AreaListService implements ServiceHandler
{
    @Override
    public String supportServiceName()
    {
        return "area.list";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        return null;
    }
}
