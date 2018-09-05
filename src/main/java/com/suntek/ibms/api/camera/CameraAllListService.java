package com.suntek.ibms.api.camera;

import com.suntek.ibms.componet.annotation.CheckType;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.componet.base.ServiceHandler;
import com.suntek.ibms.componet.controller.body.Request;
import com.suntek.ibms.componet.controller.body.Response;
import com.suntek.ibms.componet.controller.body.ResponseBody;
import com.suntek.ibms.manager.CameraManager;
import com.suntek.ibms.vo.CameraVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * 获取全部摄像机信息
 *
 * @author jimmy
 */
@Component
public class CameraAllListService extends ServiceHandler
{
    @Autowired
    private CameraManager cameraManager;

    @ParamField(name = "page", checkType = CheckType.NOT_NULL_AND_BLANK, message = "页数不能为空")
    ThreadLocal<String> page = new ThreadLocal<>();

    @ParamField(name = "page_size", checkType = CheckType.NOT_NULL_AND_BLANK, message = "页面大小不能为空")
    ThreadLocal<String> pageSize = new ThreadLocal<>();

    @Override
    public String supportServiceName()
    {
        return "camera.all_list";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        Page<CameraVo> cameraVoPage = cameraManager
                .findAll(Integer.parseInt(page.get()), Integer.parseInt(pageSize.get()));
        return new ResponseBody()
                .putData("cameras",cameraVoPage.getContent())
                .putData("total_page",cameraVoPage.getTotalPages())
                .putData("total_size",cameraVoPage.getTotalElements())
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
