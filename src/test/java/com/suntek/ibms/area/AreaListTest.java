package com.suntek.ibms.area;

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
public class AreaListTest
{
    @Autowired
    TestRestTemplate testRestTemplate;

    /**
     * 测试获取摄像头信息成功
     */
    @Test
    public void testAreaListSuccess()
    {
        Request request = new RequestBody()
                .putParams("parent_id","1")
                .build();

        String body = testRestTemplate.postForObject("/api/area/list",request,String.class);
        System.out.println(body);
    }
}
