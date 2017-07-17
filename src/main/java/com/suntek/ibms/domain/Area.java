package com.suntek.ibms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 区域
 *
 * @author jimmy
 */
@Entity
@Table(name= "sys_dept")
public class Area
{
    //区域id
    @Id
    @Column(name = "DEPT_CODE")
    private String id;

    //区域名称
    @Column(name = "DEPT_NAME")
    private String name;

    //排序顺序
    @Column(name = "DEPT_SORT")
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
