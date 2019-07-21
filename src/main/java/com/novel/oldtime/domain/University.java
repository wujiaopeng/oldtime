package com.novel.oldtime.domain;

/**
 * @Author: Peng
 * @Date: 2019/5/31 22:07
 * @Description:
 */
public class University {

    //全国所有高校
    private  Integer id;
    private  String name;
    private String called;
    private String zone;
    private Integer order;

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

    public String getCalled() {
        return called;
    }

    public void setCalled(String called) {
        this.called = called;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
