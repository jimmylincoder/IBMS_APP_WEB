package com.suntek.ibms.api.infinvoa_platform;

import com.suntek.ibms.componet.annotation.CheckType;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.componet.base.ServiceHandler;
import com.suntek.ibms.componet.controller.body.Request;
import com.suntek.ibms.componet.controller.body.Response;
import com.suntek.ibms.componet.controller.body.ResponseBody;
import com.suntek.ibms.manager.InfinvoaPlatfromManager;
import com.suntek.ibms.vo.InfinvoaPlatformVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * 获取英飞拓服务列表
 *
 * @author jimmy
 */
@Component
public class FindInfinvoaPlatformListService extends ServiceHandler
{
    @Autowired
    private InfinvoaPlatfromManager infinvoaPlatfromManager;

    @ParamField(name = "page", checkType = CheckType.NOT_NULL_AND_BLANK, message = "页数不能为空")
    ThreadLocal<String> page = new ThreadLocal<>();

    @ParamField(name = "page_size", checkType = CheckType.NOT_NULL_AND_BLANK, message = "页面大小不能为空")
    ThreadLocal<String> pageSize = new ThreadLocal<>();

    @Override
    public String supportServiceName()
    {
        return "infinvoa_platfrom.list";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        Page<InfinvoaPlatformVo> infinvoaPlatformVos = infinvoaPlatfromManager.findList(Integer.parseInt(page.get()), Integer.parseInt(pageSize.get()));
        return new ResponseBody()
                .setStatus(Response.STATUS_SUCCESS)
                .putData("total_page", infinvoaPlatformVos.getTotalPages())
                .putData("total_size", infinvoaPlatformVos.getTotalElements())
                .putData("infinvoa_platfroms",infinvoaPlatformVos.getContent())
                .bulid();
    }
}
