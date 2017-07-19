package com.suntek.ibms.api.camera;

import com.suntek.ibms.componet.Request;
import com.suntek.ibms.componet.Response;
import com.suntek.ibms.componet.ResponseBody;
import com.suntek.ibms.componet.ServiceHandler;
import com.suntek.ibms.componet.annotation.CheckType;
import com.suntek.ibms.componet.annotation.ParamField;
import com.suntek.ibms.manager.CameraManager;
import org.springframework.beans.factory.annotation.Autowired;
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
    String areaId;

    //页数
    @ParamField(name = "page",checkType = CheckType.NOT_NULL_AND_BLANK,message = "页数不能为空")
    String page;

    //搜索关键字
    @ParamField(name = "keyword")
    String keyword;

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
        return new ResponseBody()
                .putData("camera_list",cameraManager.getCameraList(areaId,keyword,Integer.parseInt(page)))
                .setStatus(Response.STATUS_SUCCESS)
                .bulid();
    }
}
