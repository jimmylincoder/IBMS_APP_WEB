package com.suntek.ibms.api.camera;

import com.suntek.ibms.componet.Request;
import com.suntek.ibms.componet.Response;
import com.suntek.ibms.componet.ResponseBody;
import com.suntek.ibms.componet.ServiceHandler;
import com.suntek.ibms.componet.annotation.CheckType;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.manager.CameraManager;
import com.suntek.ibms.vo.CameraVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取摄像机列表
 *
 * @author jimmy
 */
@Component
public class CameraListService extends ServiceHandler
{
    //区域id
    @ParamField(name = "area_id")
    ThreadLocal<String> areaId;

    //页数
    @ParamField(name = "page",checkType = CheckType.NOT_NULL_AND_BLANK,message = "页数不能为空")
    ThreadLocal<String> page;


    @Autowired
    CameraManager cameraManager;

    @Override
    public String supportServiceName()
    {
        return "camera.list";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        Page<CameraVo> cameraVoPage = cameraManager.getCameraList(areaId.get(),Integer.parseInt(page.get()));
        return new ResponseBody()
                .putData("camera_list",cameraVoPage.getContent())
                .putData("total_page",cameraVoPage.getTotalPages())
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
