package com.suntek.ibms.manager;

import com.suntek.ibms.App;
import com.suntek.ibms.componet.infinvoa.InfinvoaException;
import com.suntek.ibms.exception.InfinvoaPlatfromException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class InfinvoaManager
{
    @Autowired
    private InfinvoaPlatfromManager infinvoaPlatfromManager;

    @Test
    public void refresh() throws InfinvoaPlatfromException, IOException, InfinvoaException
    {
        infinvoaPlatfromManager.refresh();
    }
}
