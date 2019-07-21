package com.novel.oldtime.domain;

/**
 * @Author: Peng
 * @Date: 2019/5/19 10:14
 * @Description:
 */
public class Type {

    private  Integer id;
    private String name;
    private String pictPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictPath() {
        return pictPath;
    }

    public void setPictPath(String pictPath) {
        this.pictPath = pictPath;
    }
}
