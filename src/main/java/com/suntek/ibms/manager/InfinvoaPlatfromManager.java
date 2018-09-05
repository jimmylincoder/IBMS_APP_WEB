package com.suntek.ibms.manager;

import com.suntek.ibms.componet.infinvoa.InfinvoaException;
import com.suntek.ibms.domain.InfinvoaPlatform;
import com.suntek.ibms.exception.InfinvoaPlatfromException;
import com.suntek.ibms.repository.InfinvoaPlatformRepository;
import com.suntek.ibms.vo.InfinvoaPlatformVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

/**
 * 英飞拓平台业务类
 *
 * @author jimmy
 */
@Component
public class InfinvoaPlatfromManager
{
    @Autowired
    InfinvoaPlatformRepository infinvoaPlatformRepository;

    @Autowired
    private InfinvoaConvertManager infinvoaConvertManager;

    public void add(String name, String ip, String port, String uesrName, String password) throws InfinvoaPlatfromException
    {
        List<InfinvoaPlatform> infinvoaPlatformList = infinvoaPlatformRepository.findAll();
        if (infinvoaPlatformList.size() > 0)
            throw new InfinvoaPlatfromException("平台添加不能超过一个");
        InfinvoaPlatform infinvoaPlatform = new InfinvoaPlatform();
        infinvoaPlatform.setName(name);
        infinvoaPlatform.setIp(ip);
        infinvoaPlatform.setPort(Integer.parseInt(port));
        infinvoaPlatform.setUserName(uesrName);
        infinvoaPlatform.setPassword(password);
        infinvoaPlatformRepository.save(infinvoaPlatform);
    }

    public Page<InfinvoaPlatformVo> findList(int page, int pageSize)
    {
        Page<InfinvoaPlatform> infinvoaPlatforms = infinvoaPlatformRepository.findAll(new PageRequest(page - 1, pageSize));
        Page<InfinvoaPlatformVo> infinvoaPlatformVos = infinvoaPlatforms.map(new Converter<InfinvoaPlatform, InfinvoaPlatformVo>()
        {
            @Override
            public InfinvoaPlatformVo convert(InfinvoaPlatform infinvoaPlatform)
            {
                InfinvoaPlatformVo infinvoaPlatformVo = new InfinvoaPlatformVo();
                BeanUtils.copyProperties(infinvoaPlatform, infinvoaPlatformVo);
                return infinvoaPlatformVo;
            }
        });
        return infinvoaPlatformVos;
    }

    public void del(String id)
    {
        infinvoaPlatformRepository.delete(id);
    }

    public void update(String id, String name, String ip, String port, String userName, String password) throws InfinvoaPlatfromException
    {
        InfinvoaPlatform infinvoaPlatform = infinvoaPlatformRepository.findOne(id);
        if (infinvoaPlatform == null)
            throw new InfinvoaPlatfromException("该英飞拓平台不存在");
        if (!StringUtils.isEmpty(name))
            infinvoaPlatform.setName(name);
        if (!StringUtils.isEmpty(ip))
            infinvoaPlatform.setIp(ip);
        if (!StringUtils.isEmpty(port))
            infinvoaPlatform.setPort(Integer.parseInt(port));
        if (!StringUtils.isEmpty(userName))
            infinvoaPlatform.setUserName(userName);
        if (!StringUtils.isEmpty(password))
            infinvoaPlatform.setPassword(password);
        infinvoaPlatformRepository.save(infinvoaPlatform);
    }

    public void refresh() throws InfinvoaPlatfromException, IOException, InfinvoaException
    {
        List<InfinvoaPlatform> infinvoaPlatforms = infinvoaPlatformRepository.findAll();
        if (infinvoaPlatforms.size() == 0)
            throw new InfinvoaPlatfromException("未配置平台信息");
        InfinvoaPlatform infinvoaPlatform = infinvoaPlatforms.get(0);
        infinvoaConvertManager.init(infinvoaPlatform.getIp(), infinvoaPlatform.getUserName(),
                infinvoaPlatform.getPassword());
    }

    public InfinvoaPlatformVo findInfo(String id)
    {
        InfinvoaPlatformVo infinvoaPlatformVo = new InfinvoaPlatformVo();
        InfinvoaPlatform infinvoaPlatform = infinvoaPlatformRepository.findOne(id);
        BeanUtils.copyProperties(infinvoaPlatform, infinvoaPlatformVo);
        return infinvoaPlatformVo;
    }
}
