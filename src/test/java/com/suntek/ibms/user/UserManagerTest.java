package com.suntek.ibms.user;

import com.suntek.ibms.App;
import com.suntek.ibms.componet.Request;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理类测试
 *
 * @author jimmy
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserManagerTest
{
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testLoginSuccess()
    {
        Request request = new Request();
        Map<String,Object> params = new HashMap<>();
        params.put("user_name","admin");
        params.put("password","suntek");
        request.setParams(params);
        String body = testRestTemplate.postForObject("/api/user/login",request,String.class);
        System.out.println(body);
    }
}
