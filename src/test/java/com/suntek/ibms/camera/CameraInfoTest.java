package com.suntek.ibms.camera;
import com.suntek.ibms.App;
import com.suntek.ibms.componet.controller.body.Request;
import com.suntek.ibms.componet.controller.body.RequestBody;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 用户管理类测试
 *
 * @author jimmy
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CameraInfoTest
{
    @Autowired
    TestRestTemplate testRestTemplate;

    /**
     * 测试获取摄像头信息成功
     */
    @Test
    public void testCameraInfoSuccess()
    {
        Request request = new RequestBody()
                .putParams("camera_id","454")
                .build();

        String body = testRestTemplate.postForObject("/api/camera/getCameraInfo",request,String.class);
        System.out.println(body);
    }

}
