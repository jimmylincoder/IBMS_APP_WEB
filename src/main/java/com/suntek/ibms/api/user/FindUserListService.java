package com.suntek.ibms.api.user;

import com.suntek.ibms.componet.annotation.CheckType;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.componet.base.ServiceHandler;
import com.suntek.ibms.componet.controller.body.Request;
import com.suntek.ibms.componet.controller.body.Response;
import com.suntek.ibms.componet.controller.body.ResponseBody;
import com.suntek.ibms.manager.UserManager;
import com.suntek.ibms.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * 获取用户列表
 *
 * @author jimmy
 */
@Component
public class FindUserListService extends ServiceHandler
{
    @Autowired
    private UserManager userManager;

    @ParamField(name = "page", checkType = CheckType.NOT_NULL_AND_BLANK, message = "页数不能为空")
    ThreadLocal<String> page = new ThreadLocal<>();

    @ParamField(name = "page_size", checkType = CheckType.NOT_NULL_AND_BLANK, message = "页面大小不能为空")
    ThreadLocal<String> pageSize = new ThreadLocal<>();

    @Override
    public String supportServiceName()
    {
        return "user.list";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        Page<UserVo> userVos = userManager.findUserList(Integer.parseInt(page.get()), Integer.parseInt(pageSize.get()));
        return new ResponseBody()
                .setStatus(Response.STATUS_SUCCESS)
                .putData("users", userVos.getContent())
                .putData("total_page", userVos.getTotalPages())
                .putData("total_size", userVos.getTotalElements())
                .bulid();
    }
}
