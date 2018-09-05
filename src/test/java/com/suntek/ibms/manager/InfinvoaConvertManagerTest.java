package com.suntek.ibms.manager;

import com.suntek.ibms.App;
import com.suntek.ibms.componet.infinvoa.InfinvoaException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class InfinvoaConvertManagerTest
{
    @Autowired
    private InfinvoaConvertManager infinvoaManager;

    @Test
    public void testInit() throws IOException, InfinvoaException
    {
        infinvoaManager.initOrg("192.168.0.215","admin","admin");
    }


}
