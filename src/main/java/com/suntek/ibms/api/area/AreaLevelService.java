package com.suntek.ibms.api.area;

import com.suntek.ibms.componet.controller.body.Request;
import com.suntek.ibms.componet.controller.body.Response;
import com.suntek.ibms.componet.controller.body.ResponseBody;
import com.suntek.ibms.componet.base.ServiceHandler;
import com.suntek.ibms.componet.annotation.CheckType;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.manager.AreaManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 根据等级获取区域
 *
 * @author jimmy
 */
@Component
public class AreaLevelService extends ServiceHandler
{
    @Autowired
    AreaManager areaManager;

    @ParamField(name = "level",checkType = CheckType.NOT_NULL_AND_BLANK,message = "等级不能为空")
    ThreadLocal<String> level;

    @Override
    public String supportServiceName()
    {
        return "area.list_by_level";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        return new ResponseBody()
                .setStatus(Response.STATUS_SUCCESS)
                .putData("area_list",areaManager.getAreaListByLevel(level.get()))
                .bulid();
    }
}
