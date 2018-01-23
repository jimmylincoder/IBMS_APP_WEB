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
 * 摄像机列表测试
 *
 * @author jimmy
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CameraListTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testGetCameraListSuccess()
    {
        Request request = new RequestBody()
                .putParams("page","1")
                .putParams("keyword","摄像")
                .build();

        String body = testRestTemplate.postForObject("/api/camera/list",request,String.class);
        System.out.println(body);
    }
}
