package com.suntek.ibms.api.camera;

import com.suntek.ibms.componet.controller.body.Request;
import com.suntek.ibms.componet.controller.body.Response;
import com.suntek.ibms.componet.controller.body.ResponseBody;
import com.suntek.ibms.componet.base.ServiceHandler;
import com.suntek.ibms.componet.annotation.CheckType;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.manager.CameraManager;
import com.suntek.ibms.vo.CameraVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * 摄像机搜索接口
 *
 * @author jimmy
 */
@Component
public class CameraListSearchService extends ServiceHandler
{
    @Autowired
    CameraManager cameraManager;

    @ParamField(name = "page",checkType = CheckType.NOT_NULL_AND_BLANK,message = "页数不能为空")
    ThreadLocal<String> page = new ThreadLocal<>();

    @ParamField(name = "keyword",checkType = CheckType.NOT_NULL_AND_BLANK,message = "关键字不能为空")
    ThreadLocal<String> keyword = new ThreadLocal<>();

    @Override
    public String supportServiceName()
    {
        return "camera.list_by_keyword";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        Page<CameraVo> cameraVoPage = cameraManager.getCameraListByKeyword(keyword.get(),Integer.parseInt(page.get()));
        return new ResponseBody()
                .putData("camera_list",cameraVoPage.getContent())
                .putData("total_page",cameraVoPage.getTotalPages())
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
