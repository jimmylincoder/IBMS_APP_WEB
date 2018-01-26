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
 * 获取历史播放摄像机列表
 *
 * @author jimmy
 */
@Component
public class CameraHistoryListService extends ServiceHandler
{
    @ParamField(name = "page",checkType = CheckType.NOT_NULL_AND_BLANK,message = "页数不能为空")
    ThreadLocal<String> page = new ThreadLocal<>();

    @ParamField(name = "user_code",checkType = CheckType.NOT_NULL_AND_BLANK,message = "用户代码不能为空")
    ThreadLocal<String> userCode = new ThreadLocal<>();

    @Autowired
    CameraManager cameraManager;

    @Override
    public String supportServiceName()
    {
        return "camera.history_list";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        Page<CameraVo> cameraVoPage = cameraManager.getHistory(userCode.get(),Integer.parseInt(page.get()));
        return new ResponseBody()
                .putData("camera_list",cameraVoPage.getContent())
                .putData("total_page",cameraVoPage.getTotalPages())
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}

