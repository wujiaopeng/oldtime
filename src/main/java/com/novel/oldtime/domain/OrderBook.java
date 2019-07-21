package com.novel.oldtime.domain;

import java.math.BigDecimal;

/**
 * @Author: Peng
 * @Date: 2019/6/2 15:57
 * @Description:
 */
public class OrderBook {
    /*
    *订单下的书籍表
     */
    private Integer id;
    private Integer orderId;//订单id
    private Integer bid;//书本id
    private Integer number;//购买数量

    private String bookImage;
    private BigDecimal bookPrice;
    private String bookName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
