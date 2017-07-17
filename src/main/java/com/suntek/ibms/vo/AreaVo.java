package com.suntek.ibms.vo;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;

/**
 * 区域vo
 *
 * @author jimmy
 */
public class AreaVo
{
    //区域id
    @JSONField(name = "id")
    private String id;

    //区域名称
    @JSONField(name = "name")
    private String name;

    //排序顺序
    @JSONField(name = "sort")
    private String sort;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSort()
    {
        return sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }
}
