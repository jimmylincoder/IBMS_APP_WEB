package com.suntek.ibms.api.area;

import com.suntek.ibms.componet.Request;
import com.suntek.ibms.componet.Response;
import com.suntek.ibms.componet.ResponseBody;
import com.suntek.ibms.componet.ServiceHandler;
import com.suntek.ibms.componet.annotation.CheckType;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.manager.AreaManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 区域列表
 *
 * @author jimmy
 */
@Component
public class AreaListService extends ServiceHandler
{
    @Autowired
    AreaManager areaManager;

    @ParamField(name = "parent_id",checkType = CheckType.NOT_NULL_AND_BLANK,message = "父id不能为空")
    String parentId;

    @Override
    public String supportServiceName()
    {
        return "area.list";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        return new ResponseBody()
                .putData("area_list",areaManager.getAreaListByParentId(parentId))
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
