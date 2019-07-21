package com.novel.oldtime.domain;

/**
 * @Author: Peng
 * @Date: 2019/5/31 22:04
 * @Description:
 */
public class Zone {

    //全国所有省份
    private String id;
    private String name;
    private Integer sort;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
