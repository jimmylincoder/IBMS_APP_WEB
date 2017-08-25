package com.suntek.ibms.camera;

import com.suntek.ibms.App;
import com.suntek.ibms.manager.CameraControlManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 摄像机列表测试
 *
 * @author jimmy
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CameraStopTest
{
    @Autowired
    CameraControlManager cameraControlManager;

    @Test
    public void testCameraStop()
    {
        try
        {
            cameraControlManager.stop("1");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
