package com.suntek.ibms.user;

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
public class UserLoginTest
{
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testLoginSuccess()
    {
        Request request = new RequestBody()
                .setSerivce("user.login")
                .setUdid("11111")
                .putParams("user_name","admin")
                .putParams("password","suntek")
                .build();

        String body = testRestTemplate.postForObject("/api",request,String.class);
        System.out.println(body);
    }

    @Test
    public void testLoginFail()
    {
        Request request = new RequestBody()
                .putParams("user_name","admin")
                .putParams("password","123")
                .build();

        String body = testRestTemplate.postForObject("/api/user/login",request,String.class);
        System.out.println(body);
    }
}
